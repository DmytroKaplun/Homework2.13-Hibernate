package org.example.app.dao.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_seq")
    @SequenceGenerator(name = "ticket_seq", sequenceName = "seq_ticket_id", allocationSize = 1)
    private long id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "fk_client"), nullable = false)
    @ToString.Exclude
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column(name = "from_planet_id", nullable = false)
    private Planet fromPlanet;

    @Enumerated(EnumType.STRING)
    @Column(name = "to_planet_id", nullable = false)
    private Planet toPlanet;

}
