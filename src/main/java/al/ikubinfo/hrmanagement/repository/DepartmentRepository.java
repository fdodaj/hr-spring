package al.ikubinfo.hrmanagement.repository;

import al.ikubinfo.hrmanagement.entity.DepartmentEntity;
import al.ikubinfo.hrmanagement.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long>, PagingAndSortingRepository<DepartmentEntity, Long> {


}
