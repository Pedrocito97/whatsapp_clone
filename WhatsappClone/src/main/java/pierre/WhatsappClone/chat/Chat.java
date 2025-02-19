package pierre.WhatsappClone.chat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pierre.WhatsappClone.common.BaseAuditingEntity;
import pierre.WhatsappClone.message.Message;
import pierre.WhatsappClone.user.User;

import java.util.List;


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

    @ManyToOne
    @JoinColumn(name="sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name="recipient_id")
    private User recipient;

    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    @OrderBy("createdDate desc")
    private List<Message> messages;

    @Transient
    public String getChatName(final String senderId){
        if(recipient.getId().equals(senderId)){
            return sender.getFirstName() + " " + sender.getLastName();
        }
        return recipient.getFirstName() + " " + recipient.getLastName();
    }
}
