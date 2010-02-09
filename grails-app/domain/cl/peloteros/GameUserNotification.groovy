package cl.peloteros

class GameUserNotification {
    int code //el mensaje a mostrarle al usuario: 0=listo
    Date dateCreated //la fecha de creación de la invitación, se fija automáticamente
    //pertenece tanto al usuario como al juego
    static belongsTo = [user: User, game: Game]
    static constraints = {
        code inList: [0]
    }
}
