package cl.peloteros

class SecurityFilters {
    def filters = {
        //reviso la sesion
        loginCheck(controller: '(base)', action: '*') {
            before = {
                if (!session.user) {
                    //guardo la uri que quería visitar para redirigirlo al logearse
                    session.lastURI = request.forwardURI
                    //y lo notifico
                    flash.message = "Debes logearte para acceder."
                    redirect(controller: "default")//redirijo al home
                    return false//evito que se ejecuten los demas filtros
                }
                return true
            }
        }
    }
}



