import cl.peloteros.*

class SecurityFilters {
    def filters = {
        //reviso la sesion
        loginCheck(controller: '(default|user)', invert: true) {
            before = {
                if (!session.user) {
                    log.info "redirect to login"
                    //guardo la uri que quería visitar para redirigirlo al logearse
                    session.lastURI = request.forwardURI
                    //y lo notifico
                    flash.message = "Debes logearte para acceder."
                    redirect(controller: "user")//redirijo al login
                    return false//evito que se ejecuten los demas filtros
                }
                return true
            }
        }
    }
}