package cl.peloteros

class User {

    String name
    String lastName
    String login
    String password

    //tiene varios equipos y juegos en los que participa
    static hasMany = [memberships: Membership, games: UserGame]

    //restricciones sobre los datos del usuario
    static constraints = {
        name blank: false//obligatorio
        lastName blank: false//obligatorio
        login email: true, blank: false, unique: true//obligatorio, identificador de usuario
        password blank: false//obligatorio
    }

    //defino el toString para imprimir un usuario
    public String toString() {
        "$name $lastName"
    }

    //ajusto para 10 equipos
    static mapping = {
        memberships batchSize: 10
        games batchSize: 10
    }

    //funcion para conectar un equipo con un usuario
    public User addToTeams(Team team, String rol) {
        Membership.link(this, team, rol)
        return this
    }

    //funcion para conectar un game con un usuario
    public User addToGames(
        Game game, boolean llevaPelota, boolean asiste, int galletas) {
        UserGame.link(this, game, llevaPelota, asiste, galletas)
        return this
    }

    //salirse de un equipo
    public User removeFromTeams(Team team) {
        Membership.unlink(this, team)
        return this
    }

    //salirse de un juego
    public User removeFromGames(Game game) {
        UserGame.unlink(this, game)
        return this
    }
}
