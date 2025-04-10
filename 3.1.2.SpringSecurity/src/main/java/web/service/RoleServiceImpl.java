package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.entity.Role;
import web.repositories.RoleRepository;


import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Transactional
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }


    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    @Override
    public List<Role> findRolesByIds(List<Long> roleIds) {
        return roleRepository.findAllById(roleIds);
    }

}
