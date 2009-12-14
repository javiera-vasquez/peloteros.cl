package cl.peloteros

class Game {
  //el complejo deportivo involucrado
  String complex
  //la dirección del complejo
  String address
  //fecha de inicio del partido
  Date date
  //fecha de creacion del partido
  Date dateCreated
  //cantidad de jugadores requerida para llevar a cabo la pichanga
  Integer playersNeeded
  //tipo de pichanga, estan listados en la clase playField
  String gameType
  //String que indica la ubicacion de un color o quizas una foto
  String shirt1
  //idem
  String shirt2
  //El comentario de la pichanga
  String comment
  //varios usuarios y equipos
  static hasMany = [users: UserGame, teams: TeamGame]

  public String day() {
    return "$date.date"
  }

  public String camisetas() {
    if (shirt1 && shirt2) return "$shirt1 y $shirt2"
    if (shirt1) return "$shirt1"
    if (shirt2) return "$shirt2"
    return "ninguna"
  }

  public String month() {
    switch (date.month) {
      case 0: return "Enero";
      case 1: return "Febrero";
      case 2: return "Marzo";
      case 3: return "Abril";
      case 4: return "Mayo";
      case 5: return "Junio";
      case 6: return "Julio";
      case 7: return "Agosto";
      case 8: return "Septiembre";
      case 9: return "Octubre";
      case 10: return "Noviembre";
      case 11: return "Diciembre";
    }
  }

  public String year() {
    return "${1900 + date.year}"
  }

  public String cuantosFaltan() {
    return "${playersNeeded - goingPlayers()}"
  }

  //la lista de usuarios
  List users() {
    return users.collect {it.user}
  }

  //La lista de jugadores por equipo
  List players(Team team) {
    return User.withCriteria {
      games {
        eq('game', this)
        eq('team', team)
        eq('asiste', true)
      }
    }
  }

  //y la lista de equipos
  List teams() {
    return teams.collect {it.team}
  }

  //retorno el equipo al que pertenece un usuario en este partido
  public Team team(User user) {
    def team = UserGame.createCriteria().get {
      and {
        eq('user', user)
        eq('game', this)
      }
    }?.team
    return team
  }

  //funcion para poder conectar un game con un usuario
  List addToUsers(user, team, llevaPelota, asiste, galletas) {
    UserGame.link(user, team, this, llevaPelota, asiste, galletas)
    return users()
  }

  //funcion para poder conectar un juego con un equipo
  List addToTeams(team, rol) {
    TeamGame.link(team, this, rol)
    return teams()
  }

  //método que me permitirá calcular los jugadores que faltan
  int goingPlayers() {
    int players = 0
    //recorro todo el listado de usuarios
    users.each {
      //si el jugador asiste, lo sumo junto a sus galletas
      if (it.asiste) players += 1 + it.cantidadGalletas
    }
    return players
  }

  //metodo que cuenta la cantidad de jugadores inscritos en el partido y en el equipo
  int playersCount(Team team) {
    return UserGame.createCriteria().get {
      projections {
        countDistinct('user')
        and {
          eq('game', this)
          eq('team', team)
        }
      }
    }
  }

  //cantidad de jugadores de un equipo que no han confirmado
  int unconfirmedPlayers(team) {
    return team.userCount() - playersCount(team)
  }

  //método que devuelve la cantidad de galletas asistentes
  int cantidadGalletas() {
    int galletas = 0
    users.each {
      //si el jugador asiste, lo sumo junto a sus galletas
      if (it.asiste) galletas += it.cantidadGalletas
    }
    return galletas
  }

  //método que devuelve la cantidad de pelotas que se llevan
  int cantidadPelotas() {
    int pelotas = 0
    users.each {
      //si el jugador asiste, lo sumo junto a sus galletas
      if (it.asiste && it.llevaPelota) pelotas++;
    }
    return pelotas
  }

  //acepto un numero limitado de tipos de partidos, luego se expandira
  static constraints = {
    gameType(inList: ["futbol", "futbolito", "baby", "otro"])
    date(min: new Date())//la fecha de inicio tiene que ser en el futuro
    shirt1(nullable: true, blank: true)
    shirt2(nullable: true, blank: true)
    comment(nullable: true, blank: true)
    address(nullable: true, blank: true)
    //el resto de los parametros son obligatorios
  }
}
