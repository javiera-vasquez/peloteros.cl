import cl.peloteros.User

class BootStrap {

    def init = { servletContext ->
        environments{
            development{
                //agrego un usuario de prueba
                def testUser = new User(
                    name:"testUser",
                    lastName:"testUser",
                    login:"test@test.com",
                    password:"1234",
                )
                if(testUser.save()){
                    println("test user created: $testUser")
                }
                else{
                    println("test user not created")
                }
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