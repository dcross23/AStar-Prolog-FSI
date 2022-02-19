/*****************************************************************************

		Copyright (c) My Company

 Project:  PAPAYAS
 FileName: PAPAYAS.PRO
 Purpose: No description
 Written by: Visual Prolog
 Comments:
******************************************************************************/

include "papayas.inc"

domains
	peso=real
	id=integer
	fecha=real
	precio=real
	pieza=p(peso,id,fecha)
	almacen=pieza*

predicates
  colocar(almacen).
  colocar(almacen, peso, precio).

clauses
  colocar(Almacen):-
  	colocar(Almacen, 0, 0).

  /* Si no hay papayas o se han acabado, y el peso y precio acumulados son 0, significa que 
      o el almacen estaba vacío desde el principio, o que no sobra ninguna papaya */
  colocar([],0,0):-
  	write("Almacen vacio, no sobran papayas","\n").


  /* Si no hay papayas en el almacen, y el peso acumulado esta entre 0 y 2, 
      significa que han sobrado papayas y no dan para llenar una bandeja */  
  colocar([], PesoAcum, _):-
  	 PesoAcum<2,
  	 write("Sobran papayas con un peso total: ",PesoAcum,"\n\n").
  
  
  /* Si quedan papayas en el almacen, y el peso acumulado es menor que 2, significa
      que las papayas cogidas todavía no han llenado una bandeja*/
  colocar([Papaya|Resto], PesoAcum, PrecioAcum):-
  	PesoAcum<2,
  	Papaya=p(Peso,Id,Fecha),
  	write("  Papaya id \"",Id,"\" -> peso: ",Peso,"\n"),
  	NuevoPesoAcum=PesoAcum+Peso,
  	NuevoPrecioAcum=2.0*Peso+0.05*Fecha+0.10+PrecioAcum,
  	colocar(Resto, NuevoPesoAcum, NuevoPrecioAcum).
  
  
  /* Si quedan papayas en el almacen, y el peso acumulado es mayor o igual que 2,
      significa que las papayas que se han ido cogiendo llenan una bandeja */	
  colocar(Almacen, PesoAcum, PrecioAcum):-
  	PesoAcum>=2,
  	PrecioBandeja=0.30+PrecioAcum,
  	write("Bandeja con peso: ",PesoAcum," -> Coste: ",PrecioBandeja,"\n\n"),
  	colocar(Almacen, 0, 0).
  
goal
  colocar(
  	[
		p(0.273, 1400001, 1.1),
		p(0.405, 1400002, 1),
		p(0.517, 1400003, 1.1),
		p(0.533, 1400004, 1.7),
		p(0.358, 1400005, 1.5),
		p(0.562, 1400006, 1.9),
		p(0.322, 1400007, 2.4),
		p(0.494, 1400008, 1.8),
		p(0.39,  1400009, 1.6),
		p(0.281, 1400010, 2.2),
		p(0.395, 1400011, 2),
		p(0.407, 1400012, 2),
		p(0.329, 1400013, 3),
		p(0.629, 1400014, 2.7),
		p(0.417, 1400015, 1.2),
		p(0.278, 1400016, 1.4),
		p(0.583, 1400017, 2.2),
		p(0.598, 1400018, 1.9),
		p(0.271, 1400019, 1.6),
		p(0.265, 1400020, 2.1)
	]
  ).
