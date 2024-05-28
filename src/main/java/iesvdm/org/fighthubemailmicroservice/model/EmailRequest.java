package iesvdm.org.fighthubemailmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailRequest {

    // *** PROPS ***
    // *************
    private String to;
    private String subject;
    private String text;
    private String link;
    private String name;

}
