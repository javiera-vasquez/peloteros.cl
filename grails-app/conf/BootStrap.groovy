import cl.peloteros.User

class BootStrap {

    def init = { servletContext ->
        environments{
            development{
                //agrego un usuario de prueba
                def testUser = new User(
                    name:"testUser",
                    lastname:"testUser",
                    login:"test@test.com",
                    password:"1234",
                )
                testUser.save()
            }
        }
    }
    def destroy = {
        environments{
            development{
                //elimino el usuario de prueba
                User.findByName("testUser").delete()
            }
        }
    }
} 