package com.example.demo.Admin.modelo;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "boleta")
public class BoletaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nroBoleta;

    @Column(nullable = false)
    private LocalDateTime fechaEmision;

    @Column(nullable = false)
    private Integer total;

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "rutTienda")
    private TiendaModel rutTienda;

    public Long getNroBoleta() {
        return this.nroBoleta;
    }

    public void setNroBoleta(Long nroBoleta) {
        this.nroBoleta = nroBoleta;
    }

    public LocalDateTime getFechaEmision() {
        return this.fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public TiendaModel getRutTienda() {
        return this.rutTienda;
    }

    public void setRutTienda(TiendaModel rutTienda) {
        this.rutTienda = rutTienda;
    }

}
