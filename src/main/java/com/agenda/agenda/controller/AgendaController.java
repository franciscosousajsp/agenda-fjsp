package com.agenda.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.agenda.model.Agenda;
import com.agenda.agenda.repository.AgendaRepository;




@RestController
public class AgendaController {

	@Autowired
	AgendaRepository agendaRepository;
	
	/*SALVAR*/
	@PostMapping(value = "salvar")
	@ResponseBody
	public ResponseEntity<Agenda>salvar(@RequestBody Agenda agenda){
		Agenda ag = agendaRepository.save(agenda);
		
		return new ResponseEntity<Agenda>(ag, HttpStatus.CREATED);
		
	}
	
	/*LISTAR*/
	@GetMapping(value = "listaParticipantes")
	@ResponseBody
	public ResponseEntity<List<Agenda>> listaCompromissos(){
		List<Agenda> agendas = agendaRepository.findAll();
		
		return new ResponseEntity<List<Agenda>>(agendas, HttpStatus.OK);
	}
	
	/*ATUALIZAR*/
	@PutMapping(value = "atualizar")
	@ResponseBody
	public ResponseEntity<?> atualizar(@RequestBody Agenda agenda) {

		if (agenda.getId() == null) {
			return new ResponseEntity<String>("Não pode atualizar com ID nulo", HttpStatus.OK);
		}

		Agenda ag = agendaRepository.saveAndFlush(agenda);

		return new ResponseEntity<Agenda>(ag, HttpStatus.OK);
	}
	
	/*PESQUISAR POR ID*/
	@GetMapping(value = "buscarPorId")
	@ResponseBody
	public ResponseEntity<Agenda> buscarUserId(@RequestParam(name = "idAgenda") Long idAgenda) {
		Agenda agenda = agendaRepository.findById(idAgenda).get();

		return new ResponseEntity<Agenda>(agenda, HttpStatus.OK);
	}

	/*PESQUISAR POR COMPROMISSO*/
	@GetMapping(value = "buscarPorParticipante")
	@ResponseBody
	public ResponseEntity<List<Agenda>> buscarPorCompromisso(@RequestParam(name = "name") String nomeParticipante) {
		List<Agenda> agenda = agendaRepository.buscarPorCompromisso(nomeParticipante.trim().toUpperCase());

		return new ResponseEntity<List<Agenda>>(agenda, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "delete")
	@ResponseBody
	public ResponseEntity<String> delete(@RequestParam long idAgenda) {
		agendaRepository.deleteById(idAgenda);

		return new ResponseEntity<String>("compromisso deletado com sucesso", HttpStatus.OK);
	}
}
