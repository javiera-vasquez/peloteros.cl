package cl.peloteros

class Message {

    String message //el mensaje en sí
    Date date = new Date()  //la fecha de envío que es "ahora"
    User user   //el usuario que escribió el mensaje

    //el mensaje pertenece a un equipo
    static belongsTo = [team: Team]

    //vinculo una instancia de usuario con una instancia de equipo
    static Message link(User user, Team team, String message) {
        def m = new Message()
        team.addToMessages(m)
        m.message = message
        m.user = user
        m.save()
        return m
    }

    //método que devuelve el mensaje al ser impreso
    public String toString() {
        return message
    }

    static constraints = {
        message(blank: false)//el mensaje no puede ser vacío
    }
}
