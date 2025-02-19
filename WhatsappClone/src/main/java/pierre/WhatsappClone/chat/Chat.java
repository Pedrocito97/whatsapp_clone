package pierre.WhatsappClone.chat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pierre.WhatsappClone.common.BaseAuditingEntity;
import pierre.WhatsappClone.user.User;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="chat")
public class Chat extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private User sender;
    private User recipient;
    private List<Message> messages;
}
