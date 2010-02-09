import cl.peloteros.*

class BootStrap {

    def init = {servletContext ->
        environments {
            development {
                //agrego usuarios de prueba
                def aUser = new User(name: "a", lastName: "a", login: "a@a.com", password: "a").save()
                def bUser = new User(name: "b", lastName: "b", login: "b@b.com", password: "b").save()
                def cUser = new User(name: "c", lastName: "c", login: "c@c.com", password: "c").save()
                def dUser = new User(name: "d", lastName: "d", login: "d@d.com", password: "d").save()
                def eUser = new User(name: "e", lastName: "e", login: "e@e.com", password: "e").save()
                def fUser = new User(name: "f", lastName: "f", login: "f@f.com", password: "f").save()
                def gUser = new User(name: "g", lastName: "g", login: "g@g.com", password: "g").save()
                def hUser = new User(name: "h", lastName: "h", login: "h@h.com", password: "h").save()
                def iUser = new User(name: "i", lastName: "i", login: "i@i.com", password: "i").save()
                def jUser = new User(name: "j", lastName: "j", login: "j@j.com", password: "j").save()
                def kUser = new User(name: "k", lastName: "k", login: "k@k.com", password: "k").save()
                def lUser = new User(name: "l", lastName: "l", login: "l@l.com", password: "l").save()
                def mUser = new User(name: "m", lastName: "m", login: "m@m.com", password: "m").save()
                def nUser = new User(name: "n", lastName: "n", login: "n@n.com", password: "n").save()

                //agrego un equipo de prueba
                def aTeam = new Team(name: "Pichangueros", description: "tapao en brillo").save()
                def bTeam = new Team(name: "Chorrito de Agua", description: "tenis puro frio").save()
                def cTeam = new Team(name: "Come caca", description: "come caca").save()

                //asocio los usuarios de prueba
                aTeam.addToUsers(aUser, 0)
                aTeam.addToUsers(bUser, 3)
                aTeam.addToUsers(cUser, 3)
                aTeam.addToUsers(dUser, 3)
                aTeam.addToUsers(eUser, 3)
                aTeam.addToUsers(fUser, 3)
                aTeam.addToUsers(gUser, 3)
                aTeam.addToUsers(hUser, 3)
                aTeam.addToUsers(iUser, 3)
                aTeam.addToUsers(jUser, 3)
                aTeam.addToUsers(kUser, 3)
                aTeam.addToUsers(lUser, 3)
                aTeam.addToUsers(mUser, 3)
                aTeam.addToUsers(nUser, 3)

                bTeam.addToUsers(aUser, 3)
                bTeam.addToUsers(bUser, 0)
                bTeam.addToUsers(cUser, 1)
                bTeam.addToUsers(dUser, 2)
                bTeam.addToUsers(eUser, 3)
                bTeam.addToUsers(fUser, 3)
                bTeam.addToUsers(gUser, 3)
                bTeam.addToUsers(hUser, 3)
                bTeam.addToUsers(iUser, 3)
                bTeam.addToUsers(jUser, 3)
                bTeam.addToUsers(kUser, 3)
                bTeam.addToUsers(lUser, 3)
                bTeam.addToUsers(mUser, 3)
                bTeam.addToUsers(nUser, 3)

                cTeam.addToUsers(aUser, 3)
                cTeam.addToUsers(bUser, 3)
                cTeam.addToUsers(cUser, 3)
                cTeam.addToUsers(dUser, 3)
                cTeam.addToUsers(eUser, 3)
                cTeam.addToUsers(fUser, 3)
                cTeam.addToUsers(gUser, 3)
                cTeam.addToUsers(hUser, 3)
                cTeam.addToUsers(iUser, 0)
                cTeam.addToUsers(jUser, 3)
                cTeam.addToUsers(kUser, 3)
                cTeam.addToUsers(lUser, 3)
                cTeam.addToUsers(mUser, 2)
                cTeam.addToUsers(nUser, 1)

                //creo juegos de prueba
                def testGame = new Game(complex: 'Sportime', address: 'La Reina', date: new Date() + 1, gameType: 'baby', playersNeeded: 10).save()
                def testGame2 = new Game(complex: 'Zamorano', address: 'Las Condes', date: new Date() + 2, gameType: 'futbolito', playersNeeded: 12).save()
                def testGame3 = new Game(complex: 'San Juan Evangelista', address: 'Las Condes', date: new Date() + 3, gameType: 'futbol', playersNeeded: 22).save()
                def testGame4 = new Game(complex: 'Stadio Italiano', address: 'Las Condes', date: new Date() + 4, gameType: 'otro', playersNeeded: 15).save()

                //asocio equipos
                aTeam.addToGames(testGame, 0)
                aTeam.addToGames(testGame2, 0)
                bTeam.addToGames(testGame3, 0)
                cTeam.addToGames(testGame4, 0)

                //lo asocio a los usuarios
                aUser.addToGames(testGame, aTeam, true, true, 0)
                aUser.addToGames(testGame2, aTeam, true, true, 0)

                bUser.addToGames(testGame, aTeam, false, true, 0)
                cUser.addToGames(testGame2, aTeam, false, true, 0)
                dUser.addToGames(testGame3, bTeam, false, true, 0)
                eUser.addToGames(testGame4, cTeam, true, true, 0)

                fUser.addToGames(testGame, aTeam, false, true, 0)
                gUser.addToGames(testGame2, aTeam, false, true, 0)
                hUser.addToGames(testGame3, bTeam, false, true, 0)
                iUser.addToGames(testGame4, cTeam, true, true, 0)

                jUser.addToGames(testGame, aTeam, false, true, 0)
                kUser.addToGames(testGame2, aTeam, false, true, 0)
                lUser.addToGames(testGame3, bTeam, false, true, 0)
                mUser.addToGames(testGame4, cTeam, true, true, 0)

                jUser.addToGames(testGame, aTeam, false, true, 0)
                bUser.addToGames(testGame2, aTeam, false, true, 0)
                cUser.addToGames(testGame3, bTeam, false, true, 0)
                dUser.addToGames(testGame4, cTeam, true, true, 0)

                eUser.addToGames(testGame, aTeam, false, true, 0)
                fUser.addToGames(testGame2, aTeam, false, true, 0)
                gUser.addToGames(testGame3, bTeam, false, true, 0)
                hUser.addToGames(testGame4, cTeam, true, true, 0)

                //invento invitaciones
                def invite1 = new TeamUserInvitation(user: aUser, team: aTeam).save()
                def invite2 = new TeamUserInvitation(user: aUser, team: bTeam).save()

                //invento notificaciones
                def not1 = new TeamUserNotification(user: aUser, team: aTeam, code: 0).save()
                def not2 = new TeamUserNotification(user: aUser, team: aTeam, code: 1).save()
                def not3 = new GameUserNotification(user: aUser, game: testGame, code: 0).save()
            }
        }
    }
    def destroy = {
        environments {
            development {

                //elimino los juegos
                Game.list()*.delete()

                //elimino los equipos
                Team.list()*.delete()

                //elimino los usuarios de prueba
                User.list()*.delete()
            }
        }
    }
} 