package br.com.ProjetoAPI.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Role implements GrantedAuthority {

    @Id
    private String nomeRole;

    @ManyToMany
    private List<Pessoa> pessoas;

    @Override
    public String getAuthority() {
        return this.nomeRole;
    }
}
