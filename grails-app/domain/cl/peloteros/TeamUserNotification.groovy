package cl.peloteros

class TeamUserNotification {
    int code //el mensaje a mostrarle al usuario: 0=agregado, 1=borrado
    Date dateCreated //la fecha de creación de la invitación, se fija automáticamente
    //pertenece tanto al equipo como al usuario
    static belongsTo = [user: User, team: Team]
    static constraints = {
        code inList: [0,1]
    }
}
