package com.finalProject.FinalProject.service;

import com.finalProject.FinalProject.dto.DoctorDTO;
import com.finalProject.FinalProject.entity.Doctor;
import com.finalProject.FinalProject.repository.DoctorRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    public List<DoctorDTO> getAll(){
        List<Doctor> doctors = doctorRepo.findAll();
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for (Doctor d: doctors) {
            DoctorDTO doctorDTO = new DoctorDTO();
            BeanUtils.copyProperties(d,doctorDTO);
            doctorDTOS.add(doctorDTO);
        }
        return doctorDTOS;
    }

    public Doctor addData(DoctorDTO doctorDTO){
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorDTO,doctor);
        return doctorRepo.save(doctor);
    }

    public Doctor updateData(Long id,DoctorDTO doctorDTO){
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorDTO,doctor);
        return doctorRepo.save(doctor);
    }

    public DoctorDTO getById(Long id){
        Doctor doctor = doctorRepo.findById(id).get();
        DoctorDTO doctorDTO = new DoctorDTO();
        BeanUtils.copyProperties(doctor,doctorDTO);
        return doctorDTO;
    }

    public void deleteByID(Long id){
        doctorRepo.deleteById(id);
    }
}
