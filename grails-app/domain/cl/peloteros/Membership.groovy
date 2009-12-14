package cl.peloteros

class Membership {

    int userRol //0:superadmin,1:admin,2:invitee,3:normal
    int seenMessages = 0 //contador de mensajes vistos
    Date dateCreated

    //la relacion pertenece a un usuario y un equipo
    static belongsTo = [user: User, team: Team]

    //vinculo una instancia de usuario con una instancia de equipo
    static Membership link(User user, Team team, int rol) {
        def m = Membership.findByUserAndTeam(user, team)
        if (!m && user && team) {
            m = new Membership()
            user.addToMemberships(m)
            team.addToMemberships(m)
            m.userRol = rol
            m.save()
        }
        return m
    }

    //desvinculo las instancias
    static void unlink(User user, Team team) {
        def m = Membership.findByUserAndTeam(user, team)
        if (m) {
            user?.removeFromMemberships(m)
            team?.removeFromMemberships(m)
            m.delete()
        }
    }

    //método para actualizar los mensajes
    public void updateSeenMessages(int count) {
        seenMessages = count
    }

    //método para obtener los mensajes aun no vistos
    public int unseenMessages() {
        int count = Message.countByTeam(team) - seenMessages
        //reviso si el conteo es negativo por si alguien borro los mensajes
        if (count < 0) {
            seenMessages = -1 * count
            count = 0
        }
        return count
    }

    //restricciones
    static constraints = {
        userRol(inList: [0, 1, 2, 3])
        seenMessages(min: 0)
    }
}
