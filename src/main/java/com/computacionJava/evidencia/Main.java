/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.computacionJava.evidencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jajimenez
 */
public class Main {

    public static List<Usuario> usuarios = new ArrayList<>();
    public static List<Cita> citas = new ArrayList<>();
    public static List<Paciente> pacientes = new ArrayList<>();
    public static List<Medico> medicos = new ArrayList<>();
    public static String usuario = "";
    public static String contrasena = "";
    public static String tipoUsuario = "";
    public static Scanner leer = new Scanner(System.in);
    public static int option;

    public static void main(String[] args) {

        System.out.println("Cargando sistema... ");
        cargarUsuarios();
        iniciarSesion();

    }

    public static void cargarUsuarios() {

        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }

        Medico joaquin = new Medico();
        joaquin.setId(1);
        joaquin.setNombre("joaquin");
        joaquin.setEspecialida("Gastroenterología");
        Medico daniel = new Medico();
        daniel.setId(2);
        daniel.setNombre("daniel");
        daniel.setEspecialida("Neumología");
        Medico gaby = new Medico();
        gaby.setId(3);
        gaby.setNombre("gaby");
        gaby.setEspecialida("Pediatría");

        medicos.add(joaquin);
        medicos.add(daniel);
        medicos.add(gaby);

        Paciente juan = new Paciente();
        juan.setId(1);
        juan.setNombre("juan");
        Paciente jose = new Paciente();
        jose.setId(2);
        jose.setNombre("jose");
        Paciente maria = new Paciente();
        maria.setId(3);
        maria.setNombre("maria");

        pacientes.add(juan);
        pacientes.add(jose);
        pacientes.add(maria);

        citas.add(new Cita(1,"Folio cita: "+1, "23-06-2022",joaquin,jose));
        citas.add(new Cita(2,"Folio cita: "+2, "02-10-2022",daniel,jose));
        citas.add(new Cita(3,"Folio cita: "+3, "13-05-2022",gaby,jose));

        citas.add(new Cita(4,"Folio cita: "+4, "30-04-2023",joaquin,juan));
        citas.add(new Cita(5,"Folio cita: "+5, "22-11-2022",daniel,juan));
        citas.add(new Cita(6,"Folio cita: "+6, "04-06-2022",gaby,juan));

        citas.add(new Cita(7,"Folio cita: "+7, "01-01-2023",joaquin,maria));
        citas.add(new Cita(8,"Folio cita: "+8, "09-08-2022",daniel,maria));
        citas.add(new Cita(9,"Folio cita: "+9, "14-05-2022",gaby,maria));

        usuarios.add(new Usuario(1, "carlos", "1234", "med"));
        usuarios.add(new Usuario(2, "sofia", "1234", "pac"));
        usuarios.add(new Usuario(3, "cain", "1234", "admin"));
        System.out.println("Los usuarios han sido cargados: " + usuarios.size());

    }

    public static boolean validarCredenciales(String usuario, String contrasena) {
        if(usuarios.stream().anyMatch(x -> x.getNombre().equals(usuario) && x.getContrasena().equals(contrasena)  && x.getTipoUsuario().equals("med"))){
            tipoUsuario = "med";
            return true;
        }
        else if(usuarios.stream().anyMatch(x -> x.getNombre().equals(usuario) && x.getContrasena().equals(contrasena) && x.getTipoUsuario().equals("pac"))){
            tipoUsuario = "pac";
            return true;
        }
        else if(usuarios.stream().anyMatch(x -> x.getNombre().equals(usuario) && x.getContrasena().equals(contrasena) && x.getTipoUsuario().equals("admin"))){
            tipoUsuario = "admin";
            return true;
        } else
            return false;
    }

    public static void menu() {
        System.out.println("\n-----SISTEMA MEDICA TECMILENIO-----");
        if(tipoUsuario == "med"){
            System.out.println("1.- Dar de alta a un paciente\n"
                    + "2.- Crear una cita\n"
                    + "3.- Ver las citas por nombre del paciente");

            option = leer.nextInt();

            switch (option){
                case 1:
                    savePaciente();
                    break;
                case 2:
                    saveCita();
                    break;
                case 3:
                    String nombrePaciente;
                    System.out.println("Nombre paciente: ");
                    nombrePaciente = leer.next();
                    verCitasPaciente(nombrePaciente);
                    break;
            }

        } else if(tipoUsuario == "pac"){
            System.out.println("1.- Crear una cita\n"
                    + "2.- Ver las citas por nombre del paciente\n");

            option = leer.nextInt();

            switch (option){
                case 1:
                    saveCita();
                    break;
                case 2:
                    String nombrePaciente;
                    System.out.println("Nombre paciente: ");
                    nombrePaciente = leer.next();
                    verCitasPaciente(nombrePaciente);
                    break;
            }

        } else if(tipoUsuario == "admin"){
            System.out.println("1.- Dar de alta a medico\n"
                    + "2.- Dar de alta a un paciente\n"
                    + "3.- Ver las citas de todos los medicos\n"
                    + "4.- Ver las citas por nombre del medico\n"
                    + "5.- Ver las citas por nombre del paciente\n"
                    + "6.- Ver todos los medicos\n"
                    + "7.- Ver todos los pacientes\n");

            option = leer.nextInt();

            switch (option){
                case 1:
                    saveMedico();
                    break;
                case 2:
                    savePaciente();
                    break;
                case 3:
                    imprimirCitas(citas);
                    break;
                case 4:
                    String nombreMedico;
                    System.out.println("Nombre medico: ");
                    nombreMedico = leer.next();
                    verCitasMedico(nombreMedico);
                    break;
                case 5:
                    String nombrePaciente;
                    System.out.println("Nombre paciente: ");
                    nombrePaciente = leer.next();
                    verCitasPaciente(nombrePaciente);
                    break;
                case 6:
                    imprimirMedicos(medicos);
                    break;
                case 7:
                    imprimirPacientes(pacientes);
                    break;
            }
        }else {
            System.out.println("Gracias por visitar Médica TecMilenio");
            iniciarSesion();
        }

    }

    public static void saveCita() {
        try {
            int idMedico;
            String nombreMedico;
            String especialidadMedico;
            int idPaciente;
            String nombrePaciente;
            String fechaCita;
            Cita cita = new Cita();
            Medico medico = new Medico();
            Paciente paciente = new Paciente();

            System.out.println("ID Medico:");
            idMedico = leer.nextInt();
            medico.setId(idMedico);
            System.out.println("Nombre Medico:");
            nombreMedico = leer.next();
            medico.setNombre(nombreMedico);
            System.out.println("Especialidad Medico:");
            especialidadMedico = leer.next();
            medico.setEspecialida(especialidadMedico);
            System.out.println("ID Paciente:");
            idPaciente = leer.nextInt();
            paciente.setId(idPaciente);
            System.out.println("Nombre Paciente:");
            nombrePaciente = leer.next();
            paciente.setNombre(nombrePaciente);
            cita.setId(citas!=null ? citas.size()+1 : 1);
            cita.setNombreCita("Folio cita: "+cita.getId());
            cita.setMedico(medico);
            cita.setPaciente(paciente);
            System.out.println("Fecha de la cita:");
            fechaCita = leer.next();
            cita.setFecha(fechaCita);
            citas.add(cita);

            menu();
        } catch (Exception e) {
            System.out.println("Error->" + e.getMessage());
        }
    }

    public static void savePaciente() {
        try {
            String nombrePaciente;
            Paciente paciente = new Paciente();

            System.out.println("ID Paciente:");
            paciente.setId(pacientes!=null ? pacientes.size()+1 : 1);
            System.out.println(paciente.getId());
            System.out.println("Nombre Paciente:");
            nombrePaciente = leer.next();
            paciente.setNombre(nombrePaciente);
            pacientes.add(paciente);

            menu();
        } catch (Exception e) {
            System.out.println("Error->" + e.getMessage());
        }
    }

    public static void saveMedico() {
        try {
            int idMedico;
            String nombreMedico;
            String especialidadMedico;
            Medico medico = new Medico();

            System.out.println("ID Medico:");
            medico.setId(medicos!=null ? medicos.size()+1 : 1);
            System.out.println(medico.getId());
            System.out.println("Nombre Medico:");
            nombreMedico = leer.next();
            medico.setNombre(nombreMedico);
            System.out.println("Especialidad Medico:");
            especialidadMedico = leer.next();
            medico.setEspecialida(especialidadMedico);
            medicos.add(medico);

            menu();
        } catch (Exception e) {
            System.out.println("Error->" + e.getMessage());
        }
    }

    public static void verCitasPaciente(String nombrePaciente){
        boolean flag = false;
        if (citas != null) {
            for(Cita cita: citas){
                if(cita.getPaciente().getNombre().equals(nombrePaciente)){
                    flag = true;
                    System.out.println("------------------");
                    imprimirCita(cita);
                    System.out.println("------------------");
                }
            }
            if(!flag){
                System.out.println("El paciente no tiene citas agendadas.");
            }
            menu();
        } else {
            System.out.println("El paciente no tiene citas agendadas.");
            menu();
        }
    }

    public static void verCitasMedico(String nombreMedico){
        boolean flag = false;
        if (citas != null) {
            for(Cita cita: citas){
                if(cita.getMedico().getNombre().equals(nombreMedico)){
                    flag = true;
                    System.out.println("------------------");
                    imprimirCita(cita);
                    System.out.println("------------------");
                }
            }
            if(!flag){
                System.out.println("El medico no tiene citas agendadas.");
            }
            menu();
        } else {
            System.out.println("El medico no tiene citas agendadas.");
            menu();
        }
    }

    public static void imprimirCita(Cita cita){
        System.out.println(cita.getNombreCita());
        System.out.println("Medico: " + cita.getMedico().getNombre());
        System.out.println("Especialidad: " + cita.getMedico().getEspecialida());
        System.out.println("Paciente: " + cita.getPaciente().getNombre());
        System.out.println("Fecha de la cita: " + cita.getFecha());
    }

    public static void imprimirCitas(List<Cita> citas){
        for(Cita cita : citas){
            System.out.println("-------------------------");
            System.out.println(cita.getNombreCita());
            System.out.println("Medico: " + cita.getMedico().getNombre());
            System.out.println("Especialidad: " + cita.getMedico().getEspecialida());
            System.out.println("Paciente: " + cita.getPaciente().getNombre());
            System.out.println("Fecha de la cita: " + cita.getFecha());
            System.out.println("-------------------------");
        }
        menu();
    }

    public static void imprimirMedicos(List<Medico> medicos){
        for(Medico medico : medicos){
            System.out.println("-------------------------");
            System.out.println("ID: " + medico.getId());
            System.out.println("Medico: " + medico.getNombre());
            System.out.println("Especialidad: " + medico.getEspecialida());
            System.out.println("-------------------------");
        }
        menu();
    }

    public static void imprimirPacientes(List<Paciente> pacientes){
        for(Paciente paciente : pacientes){
            System.out.println("-------------------------");
            System.out.println("ID: " + paciente.getId());
            System.out.println("Medico: " + paciente.getNombre());
            System.out.println("-------------------------");
        }
        menu();
    }

    public static void iniciarSesion(){
        boolean existeUsuario;
        Scanner credenciales = new Scanner(System.in);
        System.out.println("Inicio de sesion:");
        System.out.println("Usuario:");
        usuario = credenciales.nextLine();
        System.out.println("Contraseña");
        contrasena = credenciales.nextLine();
        existeUsuario = validarCredenciales(usuario, contrasena);
        if (existeUsuario) {
            System.out.println("existe el usuario");
            menu();
            //load();

        } else {
            System.out.println("el usuario no existe");
            usuario = "";
            contrasena = "";
            menu();
        }
    }

}
