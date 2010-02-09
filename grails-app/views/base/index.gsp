<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title>Peloteros - zapatillas no incluidas</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'screen.css')}" type="text/css" media="screen, projection"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'layout.css')}" type="text/css" media="screen, projection"/>
    <g:javascript library="jquery"/>
    <script type="text/javascript" src="${resource(dir: 'js/jquery', file: 'jquery-ui-1.7.2.custom.min.js')}"></script>
    <g:javascript>
        $(document).ready(function() {
            $('#accordion').accordion({
                header: 'div.partido-info-general',
                autoHeight: false
            });
            $(".nav-option ul").css({display: "none"}); // Opera Fix
            $(".nav-option li").hover(function() {
                $(this).find('ul:first').css({visibility: "visible",display: "none"}).show(400);
            }, function() {
                $(this).find('ul:first').css({visibility: "hidden"});
            });
        });
    </g:javascript>
  </head>

  <body>
    <div id="wrapper" class="container">

      <div id="head" class="span-24">
        <a href="${createLink(controller: 'base')}"><img alt="none" class="banner" title="Peloteros" src="${resource(dir: 'css/img/', file: 'banner.png')}"/></a>
        <div id="perfil">

          <ol id="perfil-ol">
            <li><img class="round-right-3" alt="Avatar" title="${user}" src="${resource(dir: 'css/img/avatar', file: user.id+'.png') ?: resource(dir: 'css/img/avatar', file: '25.png')}"/></li>
            <li class="an-perfil"><a href="${createLink(controller: 'user', action: 'edit')}">${user}</a></li>
            <li class="a-perfil"><a href="${createLink(controller: 'user', action: 'edit')}">Perfil</a></li>
            <li class="a-perfil"><a href="${createLink(controller: 'user', action: 'logOut')}">Salir</a></li>
          </ol>

        </div>
      </div> <!-- end head -->

      <div id="tab-container" class="span-24">

        <ol id="tab-sec">
          <li class="active-hover"><a href="${createLink(controller: 'base')}">Home</a></li>
          <li><a href="${createLink(controller: 'teams')}">Equipos</a></li>
          <li><a href="${createLink(controller: 'friends')}">Amigos</a></li>
          <li><a href="${createLink(controller: 'places')}">Canchas</a></li>
          <li><a href="${createLink(controller: 'leagues')}">Ligas</a></li>
        </ol>

      </div> <!-- end tab-container -->

      <div id="center-container" class="span-24">
        <div id="left" class="span-17">
          <div id="container-center" class="span-15 prepend-1">

            <div id="partidos">

              <div id="tab-partidos">
                <span class="title-head-gris">Partidos</span>
              </div> <!-- end tab-partidos -->

              <div id="center-partidos">
                <div id="accordion">
                  <g:each var="game" in="${games}">
                    <div class="partido-info-general">
                      <div class="partido-info-option">
                        <ol>
                          <li class="info-estado">${game.month()}</li>
                          <li class="info-numero-str">${game.day()}</li>
                          <li class="info-numero-em">${game.year()}</li>
                        </ol>
                      </div> <!-- end partido-info option -->

                      <div class="partido-info-option">
                        <ol>
                          <li class="info-estado">Faltan</li>
                          <li class="info-numero-str">${game.cuantosFaltan()}</li>
                          <li class="info-numero-em">de ${game.playersNeeded}</li>
                        </ol>
                      </div><!-- end partido-info option -->

                      <div class="partido-nombre-equipo">
                        <span class="nombre-equipo-str"><a href="#">${game.team(user)}</a></span>
                      </div><!-- end partido-info-equipo -->
                      <div class="partido-dire-equipo">
                        <span class="info-equipo-srt">${game.complex}</span>
                        <span class="info-equipo-em">, ${game.address}</span>
                      </div><!-- end partido-info-equipo -->
                    </div> <!-- !partido-info-general -->
                    <div class="info-especial">

                      <div class="especial-option">

                        <h4>Tipo</h4>
                        <h5>${game.gameType}</h5>

                        <ul>
                          <li><span class="option-li">Camiseta(s):</span> ${game.camisetas()}</li>
                          <li><span class="option-li">Pelotas:</span> ${game.cantidadPelotas()}</li>
                          <li><a href="${createLink(controller: 'team', action: 'editGame', params: [team: game.team(user).toUrl(), game: game.id])}" class="retiro-partido">Editar partido</a></li>
                        </ul>

                      </div>
                      <div class="especial-galletas">

                        <h4>Galletas</h4>
                        <h5>${game.cantidadGalletas()}</h5>

                        <ul>
                          <li><span class="option-li">Llevo galletas:</span> ${game.galletas(user)}</li>
                          <li><input type="text" class="galleta-mas" value="${game.galletas(user)}"/><a href="#" class="btn-galleta">actualizar</a></li>
                        </ul>

                      </div>
                      <div class="especial-player">

                        <h4>Jugadores</h4>
                        <h5>${game.unconfirmedPlayers(game.team(user))} por confirmar</h5>
                        <ul class="option-list-especial">
                          <g:each var="player" in="${game.players(game.team(user))}">
                            <li><a href="#"><img class="round-right-3" alt="${player}" title="${player}" src="${resource(dir: 'css/img/avatar', file: '25.png')}"/></a></li>
                          </g:each>
                        </ul>

                      </div>
                      <div class="clear"></div>
                    </div> <!-- !end info especial -->
                  </g:each>
                </div>    <!-- end acordion -->
              </div><!-- end center-partidos -->
              <div id="bottom-partidos"></div><!-- end bottom-partidos -->
            </div> <!-- !end partidos -->

            <div class="title-center">
              <h3>Notificaciones</h3>
            </div>

            <ol class="notificaciones">
              <li><a href="#" class="notificacion-active round-right-3">Todos</a></li>
              <li><a href="#">Invitaciones</a></li>
              <li><a href="#">Partidos</a></li>
              <li><a href="#">Desafíos</a></li>
              <li><a href="#">Comentarios</a></li>
            </ol>
            <div class="clear"></div>

            <g:each var="t" in="${teams}">
              <g:each var="g" in="${t.futureGamesWithoutUser(user)}">
                <div class="invitacion">

                  <div class="span-2">
                    <img class=" round-right-5" alt="none" title="${t}" src="${resource(dir: 'css/img/avatar', file: '25.png')}"/>
                  </div>
                  <div class="span-13 last">
                    <span class="partido-sprite"><prettytime:display date="${g.dateCreated}" /></span><br/>
                    <p><a href="#">${t}</a> organizó un nuevo partido el <em>${g.day()} de ${g.month()}</em> a jugarse en <em>${g.lugar()}</em>.</p>
                    <span class="pregunta-notificacion">¿Quieres participar?</span><br/>

                    <a href="#" class="button verde ">Unirse</a><a href="#" class="button gris ">Desistir</a>
                  </div> <!-- end info- -->
                  <div class="clear"></div>
                </div> <!-- !end cuerpo-notificacion -->
              </g:each>
            </g:each>

            <g:each var="i" in="${invites}">
              <div class="invitacion">

                <div class="span-2">
                  <img class=" round-right-5" alt="none" title="${i.team}" src="${resource(dir: 'css/img/avatar', file: '65.png')}"/>
                </div>
                <div class="span-13 last">

                  <span class="invitacion-sprite"><prettytime:display date="${i.dateCreated}" /></span><br/>
                  <p><a href="#">${i.team}</a> te invita a que seas uno mas de sus jugadores.</p>
                  <span class="pregunta-notificacion">¿Quieres unirte?</span><br/>

                  <a href="${createLink(controller: 'team', action: 'join', params: [team: i.team.toUrl()])}" class="button verde">Unirse</a>
                  <a href="${createLink(controller: 'team', action: 'reject', params: [team: i.team.toUrl()])}" class="button gris ">Rechazar</a>

                </div> <!-- end info- -->
                <div class="clear"></div>
              </div> <!-- !end cuerpo-notificacion -->
            </g:each>

            <g:each var="n" in ="${newMemberships}">
              <div class="info-update round-right-3">
                <p class="invitacion-sprite-update">Fuiste aceptado en <a href="#">${n.team}</a> como jugador.</p>
              </div>
            </g:each>

            <g:each var="d" in ="${deletions}">
              <div class="info-update round-right-3">
                <p class="invitacion-sprite-update">Fuiste eliminado de <a href="#">${d.team}</a> como jugador.</p>
              </div>
            </g:each>

            <g:each var="g" in ="${readyGames}">
            <div class="info-update round-right-3">
              <p class="partido-sprite-update">El partido a jugarse el ${g.date()} con <a href="#">${g.team(user)}</a> está listo.</p>
            </div>
            </g:each>

          </div> <!-- end container center -->
        </div> <!-- end left -->


        <div id="right" class="span-7 last">
          <ul id="noti-fast">
            <li class="notificaciones-fast">Notificaciones</li>
            <li>
              <ul id="tipo-fast">
                <li class="invita-fast"><strong>3</strong> invitaciones nuevas</li>
                <li class="partido-fast"><strong>5</strong> partidos nuevos</li>
              </ul>
            </li>
          </ul> <!-- end noti fast -->

          <div class="modulo-right">

            <span class="head-modulo">Equipos</span> <a href="${createLink(controller: 'team', action: 'create')}" class="button make">Crear Equipo</a>

            <ul id="equipos-mod">
              <g:each var="team" in="${teams}">
                <li class="equipos-list">
                  <ul class="equipo-loop">
                    <li><img alt="none" class="round-right-5 " title="Nombre Equipo" src="${resource(dir: 'css/img/avatar', file: '40.png')}"/></li>
                    <li>
                      <ul class="info-equipo">
                        <li class="msg-list-right"><strong>${team.unseenMessages(user)}</strong> mensajes nuevos</li>
                        <li class="nombre-list-right"><a href="#" class="name">${team}</a></li>
                      </ul>
                    </li>
                  </ul>
                </li>
              </g:each>

            </ul> <!-- end equipos-mod -->

          </div> <!-- end modulo right -->
          <div class="modulo-right">
            <div class="modulo-mail">
              <ul class="ul-mail">
                <li class="tip-simbol">Porque no se puede jugar solo</li>
                <li>
                  <ul class="mail-rq">
                    <li><img alt="none" title="mail" src="${resource(dir: 'css/img', file: 'mail.png')}"/></li>
                    <li class="info-mail">comparte peloteros con tus amigos para tener más encuentros</li>

                  </ul>
                </li>
                <li><input class="input-mail" type="text" value="Escribe el correo electronico de tu amigo…"/></li>
              </ul>
            </div> <!-- ebd modulo mail -->
          </div>


          <div id="tip-right" class="round-right-5 ">
            <span class="tip-simbol">TIP<em>!</em></span> <br/>
            <p class="tip-info">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
          </div>

        </div><!-- end right -->

      </div> <!-- end center-container -->

      <div id="bottom-container" class="span-24">
      </div> <!-- end bottom-container -->

      <div id="footer">
      </div> <!-- end footer -->
    </div> <!-- end wrapper -->
  </body>
</html>


