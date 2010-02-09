package cl.peloteros

class Invitation {

    Date dateCreated
    String email//el correo al que estamos invitando
    String code//el codigo de invitación
    User user//el usuario invitado, si es que existe en sistema
    Team team//el equipo que está invitando

    //restricciones sobre la invitacion
    static constraints = {
        email email: true, blank: false
        code blank: false
        user nullable: true
        //el team no se permite nulo por convencion
    }
}
