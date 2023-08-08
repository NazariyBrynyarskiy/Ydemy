package pet.prjct.ydemy.ydemy.dao;


import pet.prjct.ydemy.ydemy.model.entity.Authority;

public interface RoleRepository {

    Authority findByUsername(String username);

    void save(Authority authority);
}
