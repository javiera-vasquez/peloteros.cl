package cl.peloteros

class BaseController {

    def index = {
        def user = User.get(session.user.id)
        def games = user.futureGames()
        def teams = user.teams()
        def invites = user.invites()
        def newMemberships = user.newMemberships()
        def deletions = user.teamDeletions()
        def readyGames = user.readyGames()
        return [
            user: user,
            games: games,
            teams: teams,
            invites: invites,
            newMemberships : newMemberships,
            deletions: deletions,
            readyGames: readyGames
        ]
    }
}
