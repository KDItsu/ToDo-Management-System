package com.dmm.task.data.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dmm.task.data.entity.Tasks;

//Repositoryクラスの位置付けは以下の2点になります。
//・一つひとつのタスク情報を保管する。（＝複数のタスクを一元管理する）
//・データベースと実際につながっており、Repositoryクラスを経由してデータを登録・取得する。


public interface TasksRepository extends JpaRepository<Tasks, Integer>, JpaSpecificationExecutor<Tasks> {

	//3. 管理者（ユーザー含む）が登録したタスクを取得する SQLおよびメソッドを用意する」についての実装コード
	// ユーザー用
	@Query("select a from Tasks a where a.date between :from and :to and name = :name")
	List<Tasks> findByDateBetween(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to, @Param("name") String name);

	// 管理者用
	@Query("select a from Tasks a where a.date between :from and :to")
	List<Tasks> findAllByDateBetween(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}


//JpaRepository<Tasks, Integer>と JpaSpecificationExecutor<Tasks>により、「このリポジトリでは Tasksクラスのデータを保管する」という意味になります！
//「@Query("select a from Tasks a where a.date between :from and :to and name = :name")」の部分が、SQLであり、このSQLがデータベースに対して実行されます。
//
//「List<Tasks> findByDateBetween(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to, @Param("name") String name);」が上記 SQLを実行するためのメソッドになります。
//from, toは取得する期間を指し、nameはタスク登録したユーザー名を指します。
//このメソッドをコントローラーで呼び出すことになります。
//
//@Param("from"), @Param("to"), @Param("name")は、
//SQLの「:from」「:to」「:name」に紐づいており、メソッドの引数で受け取った値が SQLに適用されて実行されます！

//ユーザー用に対して、管理者用は「name」関連の記述を削除しています。
//ユーザー（名）に関係なく、すべてのユーザーのタスクを取得するためになります！





