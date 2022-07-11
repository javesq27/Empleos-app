package com.proyecto.proyecto.entities;

import com.proyecto.proyecto.services.Observador;
import com.proyecto.proyecto.services.ServicioMails;
import org.springframework.mail.SimpleMailMessage;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.*;


@Entity
@Table(name = "Usuarios")
public class Usuario implements Observador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String username;
    @Column
    private String nombre;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private Integer estatus;
    @Column
    private Date fechaRegistro;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UsuarioPerfil",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idPerfil")
    )
    private List<Perfil> perfiles;

    public void agregar(Perfil tempPerfil) {
        if(perfiles == null) {
            perfiles = new LinkedList<Perfil>();
        }
        perfiles.add(tempPerfil);
    }

    public List<Perfil> getPerfiles() {
        return this.perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEstatus() {
        return this.estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public void onUpdate(ServicioMails servicioMails) {


        Properties props;
        props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.password","conteros1");
        props.put("mail.smtp.from","alexisarto@hotmail.com");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("alexisarto@hotmail.com", "conteros1");
                    }
                });
        MimeMessage simpleMailMessage = new MimeMessage(session);
        try {
            simpleMailMessage.setFrom("alexisarto@hotmail.com");
            simpleMailMessage.setRecipients(Message.RecipientType.TO,"conteros24@gmail.com");
            simpleMailMessage.setSubject("CompuEmpleos");
            simpleMailMessage.setText("Su solicitud ha sido revisada");
            Transport.send(simpleMailMessage);
        } catch (MessagingException e) {e.printStackTrace();}
    }
}
