/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.computacionJava.evidencia;

import java.util.Date;

/**
 *
 * @author jajimenez
 */
public class Cita {

    private Integer id;
    private String nombreCita;
    private String fecha;
    private Medico medico;
    private Paciente paciente;

    public Cita(){

    }

    public Cita(int id, String nombreCita, String fecha, Medico medico, Paciente paciente){
        this.id = id;
        this.nombreCita = nombreCita;
        this.fecha = fecha;
        this.medico = medico;
        this.paciente = paciente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCita() {
        return nombreCita;
    }

    public void setNombreCita(String nombreCita) {
        this.nombreCita = nombreCita;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

}
