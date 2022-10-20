# AYI Academy: Panic button

Entity:
  Marker:
    Atributos:
      name,
      lastname  (name y lastname estaban pensados mirando hacia una mejora, creando login y así pudiendo agregar a los popup estos atributos)
      longitude,
      latitude,
      softDelete
      
Service:
  createMarker,
  getAllMarkers:
    devuelve todos los markers que tienen softDelete en false,
  deleteMarkerById:
    setea el softDelete en true.
  getMarkerById.

Controller:
  createMarker,
  getAllMarkers,
  deleteMarkerById.
  
En esta versión de la app el foco estuvo enfocado en las operaciones básicas que nesecitaría una app que haga de botón de pánico.
En cuanto a una nueva versión podría estar orientada a una creación de login (que permaneza logueado para seguir facilitando el uso en casos de emergencia), para poder obtener algunos datos más específicos del usuario. Posible fusión con el proyecto de botón de pánico de Rodrigo Egea.
