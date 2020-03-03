import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_jpa_board")
@NoArgsConstructor
@Data
public class JpaBoard{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int boardIdx;

    @Column(nullable=false)
    private String title;

    private String contents;

    private int hitCnt=0;

    private String creatorId;

    private LocalDateTime createdDatetime = LocalDateTime.now();

    private String updaterId;

    private LocalDateTime updatedDateTime;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "board_idx")
    private Collection<BoardFileEntity> fileList;
}