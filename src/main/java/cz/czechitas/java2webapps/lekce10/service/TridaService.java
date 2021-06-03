package cz.czechitas.java2webapps.lekce10.service;

import cz.czechitas.java2webapps.lekce10.entity.Rodic;
import cz.czechitas.java2webapps.lekce10.entity.Student;
import cz.czechitas.java2webapps.lekce10.entity.Trida;
import cz.czechitas.java2webapps.lekce10.entity.Ucitel;
import cz.czechitas.java2webapps.lekce10.repository.RodicRepository;
import cz.czechitas.java2webapps.lekce10.repository.StudentRepository;
import cz.czechitas.java2webapps.lekce10.repository.TridaRepository;
import cz.czechitas.java2webapps.lekce10.repository.UcitelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TridaService {

    private final TridaRepository tridaRepository;
    private final RodicRepository rodicRepository;
    private final StudentRepository studentRepository;
    private final UcitelRepository ucitelRepository;

    @Autowired
    public TridaService(TridaRepository tridaRepository, RodicRepository rodicRepository, StudentRepository studentRepository, UcitelRepository ucitelRepository) {
        this.tridaRepository = tridaRepository;
        this.rodicRepository = rodicRepository;
        this.studentRepository = studentRepository;
        this.ucitelRepository = ucitelRepository;
    }

    public Page<Trida> listTrid(Pageable pageable) {
        return tridaRepository.findAll(Pageable.unpaged());
    }

    public Page<Trida> findTridaById(short id, Pageable pageable) {
        return tridaRepository.findById(id, Pageable.unpaged());
    }

    public Page<Rodic> listRodicuStudenta(Integer id, Pageable pageable) {
        return rodicRepository.findByDetiIdOrderByJmeno(id,Pageable.unpaged());
    }

    public Page<Student> listStudentuTridy(short id, Pageable pageable) {
        return studentRepository.findStudentByTridaIdOrderByPrijmeni(id, Pageable.unpaged());
    }

    public Page<Student> findById(Integer id, Pageable pageable) {
        return studentRepository.findById(id, Pageable.unpaged());
    }
}
