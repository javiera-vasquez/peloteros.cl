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

    public User addToTeams(Team team, int rol) {
        Membership.link(this, team, rol)
        return this
    }

    //funcion para conectar un game con un usuario

    public User addToGames(
        Game game, Team team, boolean llevaPelota, boolean asiste, int galletas) {
        UserGame.link(this, team, game, llevaPelota, asiste, galletas)
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

    //devuelve todos los juegos futuros en los que el jugador ha notificado su participacion

    List futureGames() {
        Date hoy = new Date()
        return UserGame.withCriteria {
            eq('user', this)
            game {
                ge('date', hoy)
            }
        }?.collect {it.game}
    }

    //devuelve la lista de todos los equipos a los que pertenece el usuario

    List teams() {
        //collect devuelve una lista
        return memberships.collect {it.team}.sort()
    }

    List invites(){
        TeamUserInvitation.findAllByUser(this)
    }

    List newMemberships(){
        TeamUserNotification.findAllByUserAndCode(this,0)
    }

    List teamDeletions(){
        TeamUserNotification.findAllByUserAndCode(this,1)
    }

    List readyGames(){
        GameUserNotification.findAllByUserAndCode(this,0)?.collect {it.game}
    }
}
