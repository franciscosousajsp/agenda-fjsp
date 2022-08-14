package com.agenda.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agenda.agenda.model.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long>{

	@Query(value = "select a from Agenda a where upper(trim(a.nomeParticipante)) like %?1%")
	List<Agenda> buscarPorCompromisso(String compromisso);
}
