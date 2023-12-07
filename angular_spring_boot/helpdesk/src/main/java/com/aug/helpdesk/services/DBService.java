package com.aug.helpdesk.services;

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
        
        Tecnico tec1 = new Tecnico(null, "Jane Soares Maia", "99915143187", "janes@email.com", "123");
		tec1.setPerfil(Perfil.ADMIN);		

		Cliente cli1 = new Cliente(null, "Linus Torvalds", "69158592119", "linus@linux.org", "12345");

		Chamado ch1 = new Chamado(1, Status.ANDAMENTO, tec1, cli1, "O Cliente deseja instalar mais memória em seu notebook", Prioridade.MEDIA);

		tecnicoRepository.save(tec1);
		clienteRepository.save(cli1);
		chamadoRepository.save(ch1);

    }
    
}
