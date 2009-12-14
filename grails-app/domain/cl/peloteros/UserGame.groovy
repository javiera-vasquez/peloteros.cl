package cl.peloteros

class UserGame {

    Boolean llevaPelota
    Boolean asiste
    int cantidadGalletas = 0
    Team team    //equipo desde el cual se une el usuario

    //la relacion pertenece a un usuario y un juego
    static belongsTo = [user: User, game: Game]

    //vinculo una instancia usuario con una instancia juego
    static UserGame link(user, team, game, llevaPelota, asiste, cantidadGalletas) {
        def ug = UserGame.findByUserAndGame(user, game)
        if (!ug) {
            ug = new UserGame()
            user?.addToGames(ug)
            game?.addToUsers(ug)
            ug.team = team
            ug.llevaPelota = llevaPelota
            ug.asiste = asiste
            ug.cantidadGalletas = cantidadGalletas
            ug.save()
        }
    }

    //desvinculo las instancias
    static void unlink(user, game) {
        def ug = UserGame.findByUserAndGame(user, game)
        if (ug) {
            user?.removeFromGames(ug)
            game?.removeFromUsers(ug)
            ug.delete()
        }
    }
}
