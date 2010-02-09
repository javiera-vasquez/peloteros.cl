package cl.peloteros

class TeamUserInvitation {

    String message //el mensaje a mostrarle al usuario
    Date dateCreated //la fecha de creación de la invitación, se fija automáticamente
    //pertenece tanto al equipo como al usuario
    static belongsTo = [user: User, team: Team]
    static constraints = {
        message blank:true, nullable:true
    }
}
