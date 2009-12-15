import cl.peloteros.*

class BootStrap {

    def init = {servletContext ->
        environments {
            development {
                //agrego un usuario de prueba
                def testUser = new User(
                        name: "testUser",
                        lastName: "testUser",
                        login: "test@test.com",
                        password: "1234",
                )
                if (testUser.save()) {
                    println("test user created: $testUser")
                }
                else {
                    println("test user not created")
                }
                //agrego un equipo de prueba
                def testTeam = new Team(
                        name: "testTeam",
                        description: "equipo de prueba"
                )
                if (testTeam.save()) {
                    println("test team created: $testTeam")
                }
                else {
                    println("test team not created")
                }
                //asocio al usuario de prueba
                testTeam.addToUsers(testUser, 0)
                //creo un juego de prueba
                def testGame = new Game(complex: 'Sportime', address: 'La Reina', date: new Date() + 1, gameType: 'baby', playersNeeded: 5)
                if (testGame.save()) {
                    println("test game created: $testGame")
                }
                else {
                    println("test game not created")
                }
                //creo un juego de prueba
                def testGame2 = new Game(complex: 'Sportime', address: 'La Reina', date: new Date() + 1, gameType: 'futbolito', playersNeeded: 12)
                if (testGame2.save()) {
                    println("test game created: $testGame")
                }
                else {
                    println("test game not created")
                }
                //creo un juego de prueba
                def testGame3 = new Game(complex: 'Sportime', address: 'La Reina', date: new Date() + 1, gameType: 'futbol', playersNeeded: 22)
                if (testGame3.save()) {
                    println("test game created: $testGame3")
                }
                else {
                    println("test game not created")
                }
                //lo asocio al equipo de prueba
                testTeam.addToGames(testGame, 0)
                testTeam.addToGames(testGame2, 0)
                testTeam.addToGames(testGame3, 0)
                //lo asocio al usuario
                testUser.addToGames(testGame, testTeam, true, true, 0)       
                testUser.addToGames(testGame2, testTeam, true, true, 2)
                testUser.addToGames(testGame3, testTeam, true, true, 1)
            }
        }
    }
    def destroy = {
        environments {
            development {
                //elimino el usuario de prueba
                User.findByName("testUser").delete()
            }
        }
    }
} 