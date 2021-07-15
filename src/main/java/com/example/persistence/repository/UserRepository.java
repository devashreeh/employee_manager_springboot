

package com.example.persistence.repository;


import com.example.entity.UserModel;
import com.example.persistence.domain.entity.User;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

	User findByEmail(String input);

	Optional<User> findByEmailAndIsDeletedAndSessionTokenAndEnabled(String email, boolean isDeleted, Object object,
			boolean enabled);

	Optional<User> findByEmailAndIsDeletedAndEnabled(String email, boolean b, boolean c);

	Optional<User> findByEmailAndIsDeleted(String email, boolean b);

	Optional<User> findByUserNameAndIsDeleted(String userName, boolean b);

	User findByResetPasswordCode(String code);

	User findByIdAndIsDeletedAndEnabled(Integer id, boolean b, boolean c);

	User findByIdAndIsDeleted(Integer id, boolean b);

	@Query(value = "select * from user where id in (select user_id from user_role where role_id in (select id from role where name=?1)) and is_deleted=false order by created_at desc limit ?2,?3 ", nativeQuery = true)
	List<User> getUserList(String name, Integer p, Integer ps);

	@Query(value = "select count(*) from user where id in (select user_id from user_role where role_id in (select id from role where name=?1)) and is_deleted=false ", nativeQuery = true)
	Integer countUserList(String name);

	Optional<User> findByEmailAndSessionTokenAndEnabled(String email, Object object, boolean b);

	@Query(value = "SELECT * FROM user ORDER BY ("
			+ "SELECT points FROM leader_board where leader_board.id = user.leader_board"
			+ ") DESC", nativeQuery = true)
	List<User> getAllLeaders();

	@Query(value = "SELECT * FROM user WHERE user.user_name LIKE %?1% ORDER BY ("
			+ "SELECT points FROM leader_board where leader_board.id = user.leader_board"
			+ ") DESC", nativeQuery = true)
	List<User> searchLeaders(String searchText);

	@Query(value = "SELECT * FROM user WHERE user.user_name LIKE %?1% AND user.id != ?2", nativeQuery = true)
	List<User> searchUsers(String key, int i);

	User findByUsername(String input);

	User save(UserModel usersModel, Class<User> class1);

	UserModel save(User user, Class<UserModel> class1);

}

