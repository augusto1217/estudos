package com.aug.helpdesk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aug.helpdesk.domain.Chamado;
import com.aug.helpdesk.domain.Cliente;
import com.aug.helpdesk.domain.Perfil;
import com.aug.helpdesk.domain.Prioridade;
import com.aug.helpdesk.domain.Status;
import com.aug.helpdesk.domain.Tecnico;
import com.aug.helpdesk.repositories.ChamadoRepository;
import com.aug.helpdesk.repositories.ClienteRepository;
import com.aug.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {

    @Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ChamadoRepository chamadoRepository;

    public void instanciaDB() {
        
        Tecnico tec1 = new Tecnico(null, "Jane Soares Maia", "00847493008", "janes@email.com", "123");
		tec1.setPerfil(Perfil.ADMIN);

		Tecnico tec2 = new Tecnico(null, "Deborah Soares Maia", "87507871010", "debinha@email.com", "1234");
		tec2.setPerfil(Perfil.TECNICO);

		Tecnico tec3 = new Tecnico(null, "Augusto Mariano", "72703423187", "augusto1217@email.com", "1234");
		tec3.setPerfil(Perfil.TECNICO);	

		Cliente cli1 = new Cliente(null, "Linus Torvalds", "48605631074", "linus@linux.org", "12345");
		Cliente cli2 = new Cliente(null, "Bill Gates", "21484128095", "billgates@microsoft.com", "12345");
		Cliente cli3 = new Cliente(null, "Steve Jobs", "87996088047", "stevejobs@apple.com", "12345");

		Chamado ch1 = new Chamado(null, Status.ANDAMENTO, tec1, cli1, "O Cliente deseja instalar mais memória em seu notebook", Prioridade.MEDIA);
		Chamado ch2 = new Chamado(null, Status.ABERTO, tec1, cli1, "O Cliente deseja instalar uma impressora", Prioridade.BAIXA);
		Chamado ch3 = new Chamado(null, Status.ABERTO, tec2, cli1, "O Cliente deseja instalar uma impressora", Prioridade.BAIXA);
		Chamado ch4 = new Chamado(null, Status.ENCERRADO, tec2, cli2, "O Cliente deseja configurar wi-fi", Prioridade.BAIXA);

		tecnicoRepository.saveAll(List.of(tec1, tec2, tec3));
		clienteRepository.saveAll(List.of(cli1,cli2,cli3));
		chamadoRepository.saveAll(List.of(ch1,ch2,ch3,ch4));

    }
    
}
