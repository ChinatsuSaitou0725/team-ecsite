package jp.co.internous.oasis.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.oasis.model.domain.MstUser;

/**
 * mst_userにアクセスするDAO
 * @author インターノウス
 *
 */
@Mapper
public interface MstUserMapper {
	/**
	 * ユーザー情報を登録する
	 * @param user ユーザー情報
	 * @return 登録件数
	 */
	@Insert("insert into mst_user (user_name,password,family_name,first_name,family_name_kana,first_name_kana,gender) value (#{userName}, #{password},#{familyName},#{firstName},#{familyNameKana},#{firstNameKana},#{gender})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insert(MstUser user);
	
	/**
	 * ユーザー名とパスワードを条件にユーザー情報を取得する
	 * @param userName ユーザー名
	 * @param password パスワード
	 * @return ユーザー情報
	 */
	@Select("select * from mst_user where user_name = #{userName} and password = #{password}")
	MstUser findByUserNameAndPassword(
			@Param("userName") String userName,
			@Param("password") String password);
	
	/**
	 * ユーザ名を条件に件数を取得する
	 * @param userName ユーザー名
	 * @return 件数
	 */
	@Select("select count(id) from mst_user where user_name = #{userName}")
	int findCountByUserName(String userName);

	/**
	 * ユーザー名を条件にパスワードを更新する
	 * @param userName ユーザー名
	 * @param password パスワード
	 * @return 更新件数
	 */
	@Update("update mst_user set password = #{password} where user_name = #{userName}")
	int updatePassword(
			@Param("userName") String userName,
			@Param("password") String password);
	
}
