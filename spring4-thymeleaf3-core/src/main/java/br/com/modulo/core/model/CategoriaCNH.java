package br.com.modulo.core.model;

public enum CategoriaCNH {

	/**
	 * "A" Condutor de veículo motorizado de duas ou três rodas, com ou sem
	 * carro lateral. Ex.: Motocicleta, Ciclomotor, Motoneta ou Triciclo.
	 */
	A,

	/**
	 * "B" Condutor de veículos, cujo peso bruto total não exceda a três mil e
	 * quinhentos quilogramas ou cuja lotação não exceda a 08 (oito) lugares,
	 * excluído o do motorista; contemplando a combinação de unidade acoplada
	 * reboque, desde que a soma dos dois não ultrapasse 3500 KG. Ex.:
	 * Automóvel, caminhonete, camioneta, utilitário.
	 */
	B,

	/**
	 * "C" Condutor de veículos, utilizados em transporte de carga, cujo peso
	 * bruto total exceda a três mil e quinhentos quilogramas. O trator de roda,
	 * o trator de esteira, o trator misto ou o equipamento automotor destinado
	 * à movimentação de cargas ou execução de trabalho agrícola, de
	 * terraplenagem, de construção ou de pavimentação. Combinação de veículos
	 * em que a unidade acoplada, reboque, não exceda a 6.000 kg. Todos os
	 * veículos abrangidos pela categoria "B". Ex: Caminhão.
	 */
	C,

	/**
	 * "D" Condutor de veículos, utilizados no transporte de passageiros, cuja
	 * lotação exceda a 08 passageiros, excluindo o motorista. Todos os veículos
	 * abrangidos nas categorias "B" e "C". Ex: Microônibus, Ônibus.
	 *
	 */
	D,

	/**
	 * "E" Condutor de combinação de veículos em que a unidade tratora se
	 * enquadre nas categorias B, C ou D e cuja unidade acoplada, reboque, semi
	 * reboque, trailer ou articulada tenha 6.000 kg (seis mil quilogramas) ou
	 * mais de peso bruto total, ou cuja lotação exceda a 8 (oito) lugares.
	 * Condutor de combinação de veículos com mais de uma unidade tracionada,
	 * independentemente da capacidade de tração ou do peso bruto total. Ex.:
	 * Veículo com dois reboques acoplados.
	 *
	 */
	E,

	/**
	 * "ACC" Condutor de veículos de duas ou três rodas com potência até 50
	 * cilindradas. Ex: Ciclomotores. A Resolução CONTRAN nº 315/2008 estabelece
	 * a equiparação dos veículos ciclo- elétricos aos ciclomotores.Para os
	 * efeitos de equiparação ao ciclomotor,entende- se como ciclo- elétrico
	 * todo veículo de duas ou três rodas, provido de motor de propulsão
	 * elétrica com potência máxima de 4 kW (quatro kilowatts) dotados ou não de
	 * pedais acionados pelo condutor, cujo peso máximo, incluindo condutor,
	 * passageiro e carga, não exceda 140 kg (cento e quarenta quilogramas) e
	 * cuja velocidade máxima declarada pelo fabricante não ultrapasse 50 km/h
	 * (cinquenta quilômetros por hora). Inclui-se nesta definição de
	 * ciclo-elétrico a bicicleta dotada originalmente de motor elétrico, bem
	 * como aquela que tiver este dispositivo motriz agregado posteriormente à
	 * sua estrutura.
	 *
	 * Autorização para conduzir ciclomotores
	 */
	ACC,

	/**
	 * "MOTOR-CASA" Até 6 toneladas categoria B, acima de 6 toneladas categoria
	 * C, caso o motor-casa tenha acima de 8 passageiros excluindo o motorista,
	 * categoria D.
	 */
	MOTOR_CASA_B, MOTOR_CASA_C, MOTOR_CASA_D,

	AB, AC, AD, AE;

}
