package cl.peloteros

class UserController {

    def defaultAction = 'login'

    def login = {
        if(request.method == 'POST'){
            //busco el usuario
            User user = User.findByLoginAndPassword(params.login,params.password)
            if(user){
                session.user = user
                redirect(controller: 'base')
            }
        }
    }

    def logOut = {
        session.user = null
        redirect(controller: 'default')
    }
}
