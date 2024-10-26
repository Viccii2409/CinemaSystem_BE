package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "UserNotification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserNotificationID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "NotificationID", nullable = false)
    private Notification notification;

}
