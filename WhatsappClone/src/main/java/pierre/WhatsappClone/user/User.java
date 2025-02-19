package pierre.WhatsappClone.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pierre.WhatsappClone.chat.Chat;
import pierre.WhatsappClone.common.BaseAuditingEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User extends BaseAuditingEntity {

    private static final int LAST_ACTIVE_INTERVAL = 5;
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime lastSeen; // pour voir si le user est online ou pas

    @OneToMany(mappedBy = "sender")
    private List<Chat> chatAsSender;
    @OneToMany(mappedBy = "recipient")
    private List<Chat> chatAsRecipient;

    //@transient is for temporary operations, not to store in DB
    @Transient
    public boolean isUserOnline() {
        //pour comprendre : si le user est lastseen a 10:04
        //maintenant c'est 10:09 -> online if 10:11 -> offline
        return lastSeen != null && lastSeen.isAfter(LocalDateTime.now().minusMinutes(LAST_ACTIVE_INTERVAL));
    }

}
