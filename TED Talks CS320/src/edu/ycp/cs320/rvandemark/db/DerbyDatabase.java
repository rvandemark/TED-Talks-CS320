package edu.ycp.cs320.rvandemark.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import edu.ycp.cs320.rvandemark.model.Admin;
import edu.ycp.cs320.rvandemark.model.Assignment;
import edu.ycp.cs320.rvandemark.model.Pair;
import edu.ycp.cs320.rvandemark.model.Review;
import edu.ycp.cs320.rvandemark.model.SpecificVideoAssignment;
import edu.ycp.cs320.rvandemark.model.User;
import edu.ycp.cs320.rvandemark.model.Video;
import edu.ycp.cs320.rvandemark.model.VideoByDisciplineAssignment;
import edu.ycp.cs320.rvandemark.model.YCPClass;

public class DerbyDatabase {
	private static final int MAX_ATTEMPTS = 10;
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	
	public static void main(String[] args) throws IOException, SQLException {
		DerbyDatabase db = new DerbyDatabase();
		
		System.out.println("Enter 0 to create and initialize some data. Anything else for only other tasks.");
		Scanner reader = new Scanner(System.in);
		if (reader.nextLine().equals("0")) {
			db.init();
			
		}
		
		reader.close();
		
		System.out.println("Done.");
		
		//ArrayList<String> disciplines = new ArrayList<String>();
		//disciplines.addAll(db.getAllDisciplines());
		//for(int i = 0; i< db.getAllDisciplines().size();i++ ){
			//System.out.println(disciplines.get(i).toString());
		//}
		
	}
	
	public void init() throws IOException {
		System.out.println("Creating tables...");
		createTables();

		System.out.println("Adding data...");
		String[] disciplines = {
			"Civil Engineering", "Computer Engineering", "Computer Science", "Graphic Design", "Creativity",
			"Physics", "Mathematics", "Psychology", "Skateboarding", "Rock Climbing", "MMA", "Entrepreneurship"
		};
		for (int i = 0; i < disciplines.length; i++) insertDiscipline(disciplines[i]);
		
		insertUser("rvandemark@ycp.edu", "rvandy", "p@$$word", "Vandemark", "Nick", new int[]{2,3,5,6,7,8,10}, false);
		insertUser("emoore13@ycp.edu", "Ezzypoo", "p@$$word", "Moore", "Ezra", new int[]{3,4,5,9,10}, false);
		insertUser("klangrill@ycp.edu", "langrillk", "p@$$word", "Langrill", "Kyle", new int[]{2,3,7,9,12}, false);
		insertUser("jdebose@ycp.edu", "Jo$hua", "p@$$word", "DeBose", "Josh", new int[]{2,3,5,10,12}, false);
		
		insertUser("shamilton@ycp.edu", "fake CE", "p@$$word", "Hamilton", "Scott", new int[]{1,3,5,6,7,12}, true);
		insertUser("djhake2@ycp.edu", "Prof Ponytail", "p@$$word", "Hake", "Don", new int[]{1,2,4,9}, true);
		
		String[] URLs = {
			"https://www.ted.com/talks/thomas_thwaites_how_i_built_a_toaster_from_scratch",
			"https://www.ted.com/talks/matt_ridley_when_ideas_have_sex",
			"https://www.ted.com/talks/robert_wright_on_optimism",
			"https://www.ted.com/talks/martin_rees_asks_is_this_our_final_century",
			"https://www.ted.com/talks/stephen_petranek_counts_down_to_armageddon",
			"https://www.ted.com/talks/fred_jansen_how_to_land_on_a_comet",
			"https://www.ted.com/talks/katie_bouman_what_does_a_black_hole_look_like",
			"https://www.ted.com/talks/wendy_freedman_this_new_telescope_might_show_us_the_beginning_of_the_universe",
			"https://www.ted.com/talks/sanjay_dastoor_a_skateboard_with_a_boost",
			"https://www.ted.com/talks/bill_ford_a_future_beyond_traffic_gridlock",
			"https://www.ted.com/talks/travis_kalanick_uber_s_plan_to_get_more_people_into_fewer_cars",
			"https://www.ted.com/talks/chris_urmson_how_a_driverless_car_sees_the_road",
			"https://www.ted.com/talks/golan_levin_on_software_as_art",
			"https://www.ted.com/talks/golan_levin_ted2009",
			"https://www.ted.com/talks/vik_muniz_makes_art_with_wire_sugar",
			"https://www.ted.com/talks/jimmy_nelson_gorgeous_portraits_of_the_world_s_vanishing_people",
			"https://www.ted.com/talks/stephen_wilkes_the_passing_of_time_caught_in_a_single_photo",
			"https://www.ted.com/talks/david_griffin_on_how_photography_connects",
			"https://www.ted.com/talks/erik_johansson_impossible_photography",
			"https://www.ted.com/talks/edward_burtynsky_on_manufactured_landscapes"
		};
		
		Random rand = new Random();
		int t, n;
		for (int i = 0; i < URLs.length; i++) {
			t = rand.nextInt(5) + 1;
			n = rand.nextInt(t) + 1;
			Video v = new Video(URLs[i], t, n);
			insertVideo(v.getUrl(), v.getEmbedUrl(), v.getName(), v.getSpeaker(), v.getThumbnailUrl(),
				v.getTotalRating(), v.getNumRatings(), v.getRating(), v.getUploadMonth(), v.getUploadDay(), v.getUploadYear(), v.getDisciplineLine());
		}
		
		insertReview(1, 1, "Here is an example review", 4, 10, 2017, 3, 3, false, false, false, false, false, false, false);
		insertReview(2, 2, "Here is a second example review", 6, 5, 2017, -1, 1, false, false, false, false, false, false, false);
		insertReview(3, 3, "You know the deal by now I hope", 12, 9, 2016, 32, 1, false, false, false, false, false, false, false);
		insertReview(4, 4, "Shocker, another review...", 4, 20, 2016, 19, 3, false, false, false, false, false, false, false);
		insertReview(1, 5, "My review is short and doesn't really mean much", 10, 10, 2017, 3, 3, false, false, false, false, false, false, false);
		insertReview(2, 6, "*Insert in depth review here*", 6, 4, 2017, -1, 1, false, false, false, false, false, false, false);
		insertReview(1, 3, "OMG review review review", 8, 18, 2017, 3, 3, false, false, false, false, false, false, false);
		insertReview(2, 4, "Blah blah blah", 6, 5, 2017, -1, 1, false, false, false, false, false, false, false);
		insertReview(3, 6, "Here's my review deal with it", 11, 9, 2016, 32, 1, false, false, false, false, false, false, false);
		insertReview(4, 5, "Subpar review", 9, 9, 2016, 19, 3, false, false, false, false, false, false, false);
		insertReview(1, 2, "I lack creativity", 1, 1, 2017, 3, 3, false, false, false, false, false, false, false);
		insertReview(2, 1, "Filler text", 7, 7, 2017, -1, 1, false, false, false, false, false, false, false);
		
		insertYCPClass(1, "Intro to Civil Engineering", "CVE100", 101);
		insertYCPClass(2, "Software Engineering and Design", "CS320", 101);
		insertYCPClass(2, "Software Engineering and Design", "CS320", 102);
		
		insertSpecificVideoAssignment(1, "Bridge Video", 3, 21, 2017, 3, 28, 2017, "Impossible photography");
		insertSpecificVideoAssignment(2, "Bridge Video", 3, 21, 2017, 3, 28, 2017, "Impossible photography");
		insertVideoByDisciplineAssignment(2, "How to Not Fail", 3, 14, 2017, 3, 22, 2017, "Software", 2);
		
		System.out.println("Initialized...");
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:ted_talks.db;create=true");
		conn.setAutoCommit(false);
		return conn;
	}

	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();

		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;

			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}

			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}

			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement[] stmt = new PreparedStatement[10];

				try {
					stmt[0] = conn.prepareStatement(
							"create table users (" +
									"	user_id integer primary key " +
									"		generated always as identity (start with 1, increment by 1), " +
									"	email varchar(56)," +
									"	screen_name varchar(32)," +
									"	password varchar(32)," +
									"	last_name varchar(40)," +
									"	first_name varchar(40)," +
									"   total_points integer" +
									")"
							);	
					stmt[0].executeUpdate();
					
					stmt[1] = conn.prepareStatement(
							"create table admins (" +
									"	admin_id integer primary key " +
									"		generated always as identity (start with 1, increment by 1), " +
									"	user_id integer" +
									")"
							);	
					stmt[1].executeUpdate();
					
					stmt[2] = conn.prepareStatement(
							"create table disciplines (" +
									"	discipline_id integer primary key " +
									"		generated always as identity (start with 1, increment by 1), " +
									"	name varchar(72)" +
									")"
							);
					stmt[2].executeUpdate();

					stmt[3] = conn.prepareStatement(
							"create table user_disciplines (" +
									"	user_id integer constraint user_id references users," +
									"	discipline_id integer constraint discipline_id references disciplines" +
									")"
							);
					stmt[3].executeUpdate();
					
					stmt[4] = conn.prepareStatement(
							"create table videos (" +
									"	video_id integer primary key " +
									"		generated always as identity (start with 1, increment by 1)," +
									"	url varchar(250)," +
									"	embed_url varchar(250)," +
									"	name varchar(70)," +
									"	speaker varchar(32)," +
									"	thumbnail_url varchar(250)," +
									"	total_rating integer," +
									"	num_ratings integer," +
									"	rating double," +
									"	upload_month integer," +
									"	upload_day integer," +
									"	upload_year integer," +
									"	discipline_line varchar(250)" +
									")"
							);
					stmt[4].executeUpdate();
					
					stmt[5] = conn.prepareStatement(
							"create table reviews (" +
									"	review_id integer primary key " +
									"		generated always as identity (start with 1, increment by 1)," +
									"	user_id integer," +
									"	video_id integer constraint video_id references videos," +
									"	text varchar(1250)," +
									"	post_month integer," +
									"	post_day integer," +
									"	post_year integer," +
									"	score integer," +
									"	value integer," +
									"	mustSee integer," +
									"	greatForEngineer integer," +
									"	greatForAnyone integer," +
									"	interesting integer," +
									"	limited integer," +
									"	outdated integer," +
									"	notRecommended integer" +
									")"
							);
					stmt[5].executeUpdate();
					
					stmt[5] = conn.prepareStatement(
							"create table classes (" +
									"	class_id integer primary key " +
									"		generated always as identity (start with 1, increment by 1)," +
									"	admin_id integer constraint admin_id references admins," +
									"	name varchar(50)," +
									"	prefix varchar(6)," +
									"	section integer" +
									")"
							);
					stmt[5].executeUpdate();
					
					stmt[6] = conn.prepareStatement(
							"create table assignments (" +
									"	assignment_id integer primary key " +
									"		generated always as identity (start with 1, increment by 1)," +
									"	class_id integer constraint class_id references classes," +
									"	name varchar(100)," +
									"	assign_month integer," +
									"	assign_day integer," +
									"	assign_year integer," +
									"	due_month integer," +
									"	due_day integer," +
									"	due_year integer" +
									")"
							);
					stmt[6].executeUpdate();
					
					stmt[7] = conn.prepareStatement(
							"create table specific_assignments (" +
									"	specific_assignment_id integer primary key " +
									"		generated always as identity (start with 1, increment by 1)," +
									"	assignment_id integer," +
									"	video_id integer" +
									")"
							);
					stmt[7].executeUpdate();
					
					stmt[8] = conn.prepareStatement(
							"create table discipline_assignments (" +
									"	discipline_assignment_id integer primary key " +
									"		generated always as identity (start with 1, increment by 1)," +
									"	assignment_id integer," +
									"	discipline varchar(72)," +
									"	video_count integer" +
									")"
							);
					stmt[8].executeUpdate();
					
					stmt[9] = conn.prepareStatement(
							"create table user_classes (" +
									"	class_id integer," +
									"	user_id integer" +
									")"
							);
					stmt[9].executeUpdate();
					
					return true;
				} finally {
					for (int i = 0; i < stmt.length; i++) DBUtil.closeQuietly(stmt[i]);
				}
			}
		});
	}
	
	public void insertUser(String email, String screen_name, String password, String last_name, String first_name, int[] discipline_ids, boolean isAdmin) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement insertUser = null;
				PreparedStatement getUserID = null;
				ResultSet result = null;
				PreparedStatement insertUserDiscipline = null;
				PreparedStatement insertAdmin = null;

				try {
					insertUser = conn.prepareStatement(
						"insert into users (email, screen_name, password, last_name, first_name, total_points) "
						+ "values (?, ?, ?, ?, ?, ?)"
					);
					insertUser.setString(1, email);
					insertUser.setString(2, screen_name);
					insertUser.setString(3, password);
					insertUser.setString(4, last_name);
					insertUser.setString(5, first_name);
					insertUser.setInt(6, 0);
					insertUser.executeUpdate();
					
					getUserID = conn.prepareStatement(
						"select user_id from users where email = ?"
					);
					getUserID.setString(1, email);
					
					result = getUserID.executeQuery();
					Integer user_id = null;
					
					if (result.next()) {
						user_id = result.getInt(1);
					} else {
						throw new SQLException("Error finding user by email. Failure to add user disciplines.");
					}
					
					for (int i = 0; i < discipline_ids.length; i++) {
						insertUserDiscipline( user_id, discipline_ids[i]);
					}
					
					if (isAdmin) {
						insertAdmin = conn.prepareStatement(
							"insert into admins (user_id) values (?)"
						);
						insertAdmin.setInt(1, user_id);
						
						insertAdmin.executeUpdate();
					}
				} finally {
					DBUtil.closeQuietly(insertUser);
					DBUtil.closeQuietly(getUserID);
					DBUtil.closeQuietly(result);
					DBUtil.closeQuietly(insertUserDiscipline);
					DBUtil.closeQuietly(insertAdmin);
				}
				
				return true;
			}
		});
	}
	
	public void insertDiscipline(String disc) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement insertDiscipline = null;

				try {
					insertDiscipline = conn.prepareStatement(
						"insert into disciplines (name) values (?)"
					);
					insertDiscipline.setString(1, disc);
					insertDiscipline.executeUpdate();
				} finally {
					DBUtil.closeQuietly(insertDiscipline);
				}
				
				return true;
			}
		});
	}
	
	public void insertUserDiscipline( Integer user_id, Integer discipline_id) {
		executeTransaction(new Transaction<Boolean>() {
		@Override
		public Boolean execute(Connection conn) throws SQLException {
		PreparedStatement insertUserDiscipline = null;

		try {
			insertUserDiscipline = conn.prepareStatement(
				"insert into user_disciplines (user_id, discipline_id) values (?, ?)"
			);
			insertUserDiscipline.setInt(1, user_id);
			insertUserDiscipline.setInt(2, discipline_id);
			insertUserDiscipline.executeUpdate();
		} finally {
			DBUtil.closeQuietly(insertUserDiscipline);
		}
		
		System.out.println("inserted: [" + user_id + "," + discipline_id + "]");
		return true;
		}
	});
	}
	
	public Integer insertVideo(String url, String embedUrl, String name, String speaker, String thumbnailUrl, int totalRating, int numRatings, double rating, int uploadMonth, int uploadDay, int uploadYear, String disciplineLine) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement insertVideo = null;
				PreparedStatement search = null;
				ResultSet resultSet1 = null;
				
				Integer video_id = 0;
				
				try {
					insertVideo = conn.prepareStatement(
						"insert into videos" +
							" (url, embed_url, name, speaker, thumbnail_url, total_rating," +
							" num_ratings, rating, upload_month, upload_day, upload_year, discipline_line)" +
							" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
					);
					insertVideo.setString(1, url);
					insertVideo.setString(2, embedUrl);
					insertVideo.setString(3, name);
					insertVideo.setString(4, speaker);
					insertVideo.setString(5, thumbnailUrl);
					insertVideo.setInt(6, totalRating);
					insertVideo.setInt(7, numRatings);
					insertVideo.setDouble(8, rating);
					insertVideo.setInt(9, uploadMonth);
					insertVideo.setInt(10, uploadDay);
					insertVideo.setInt(11, uploadYear);
					insertVideo.setString(12, disciplineLine);
					insertVideo.executeUpdate();
					
					search = conn.prepareStatement(
						"select video_id from videos where url = ? and name = ?"
					);
					search.setString(1, url);
					search.setString(2, name);
					resultSet1 = search.executeQuery();
					
					if (resultSet1.next()) {
						video_id = resultSet1.getInt(1);
					}
					
					return video_id;
				} 
				
				finally {
					DBUtil.closeQuietly(insertVideo);
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(search);
				}
				
			}
		});
	}
	
	public void insertReview(Integer user_id, Integer video_id, String text, Integer post_month, Integer post_day,
			Integer post_year, Integer score, Integer value, Boolean mustSee, Boolean greatForEngineer,
			Boolean greatForAnyone, Boolean interesting, Boolean limited, Boolean outdated, Boolean notRecommended) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement insertReview = null;

				try {
					insertReview = conn.prepareStatement(
						"insert into reviews (user_id, video_id, text, post_month, post_day, post_year, score, value, " +
								"mustSee, greatForEngineer, greatForAnyone, interesting, limited, outdated, notRecommended) " +
								"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
					);
					insertReview.setInt(1, user_id);
					insertReview.setInt(2, video_id);
					insertReview.setString(3, text);
					insertReview.setInt(4, post_month);
					insertReview.setInt(5, post_day);
					insertReview.setInt(6, post_year);
					insertReview.setInt(7, score);
					insertReview.setInt(8, value);
					insertReview.setBoolean(9, mustSee);
					insertReview.setBoolean(10, greatForEngineer);
					insertReview.setBoolean(11, greatForAnyone);
					insertReview.setBoolean(12, interesting);
					insertReview.setBoolean(13, limited);
					insertReview.setBoolean(14, outdated);
					insertReview.setBoolean(15, notRecommended);
					insertReview.executeUpdate();
				} finally {
					DBUtil.closeQuietly(insertReview);
				}
				
				return true;
			}
		});
	}
	
	public void insertYCPClass(int admin_id, String name, String prefix, int section) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement insertYCPClass = null;

				try {
					insertYCPClass = conn.prepareStatement(
						"insert into classes (admin_id, name, prefix, section)" +
								" values (?, ?, ?, ?)"
					);
					insertYCPClass.setInt(1, admin_id);
					insertYCPClass.setString(2, name);
					insertYCPClass.setString(3, prefix);
					insertYCPClass.setInt(4, section);
					insertYCPClass.executeUpdate();
				} finally {
					DBUtil.closeQuietly(insertYCPClass);
				}
				
				return true;
			}
		});
	}
	
	public Integer insertAssignment(int class_id, String name, int assign_month, int assign_day, int assign_year, int due_month, int due_day, int due_year) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				Integer assignment_id = null;
				PreparedStatement insertAssignment = null;
				PreparedStatement getAssignment = null;
				ResultSet result = null;

				try {
					insertAssignment = conn.prepareStatement(
						"insert into assignments (class_id, name, assign_month, assign_day, assign_year, due_month, due_day, due_year)" +
								" values (?, ?, ?, ?, ?, ?, ?, ?)"
					);
					insertAssignment.setInt(1, class_id);
					insertAssignment.setString(2, name);
					insertAssignment.setInt(3, assign_month);
					insertAssignment.setInt(4, assign_day);
					insertAssignment.setInt(5, assign_year);
					insertAssignment.setInt(6, due_month);
					insertAssignment.setInt(7, due_day);
					insertAssignment.setInt(8, due_year);
					insertAssignment.executeUpdate();
					
					getAssignment = conn.prepareStatement(
						"select * from assignments where class_id = ? and name = ?"
					);
					getAssignment.setInt(1, class_id);
					getAssignment.setString(2, name);
					
					result = getAssignment.executeQuery();
					
					if (result.next()) {
						assignment_id = result.getInt(1);
					}
				} finally {
					DBUtil.closeQuietly(insertAssignment);
					DBUtil.closeQuietly(getAssignment);
					DBUtil.closeQuietly(result);
				}
				
				return assignment_id;
			}
		});
	}
	
	public void insertSpecificVideoAssignment(int class_id, String name, int assign_month, int assign_day, int assign_year, int due_month, int due_day, int due_year, String videoName) {
		if (videoExistsByName(videoName)) {
			executeTransaction(new Transaction<Boolean>() {
				@Override
				public Boolean execute(Connection conn) throws SQLException {
					Integer assignment_id = insertAssignment(class_id, name, assign_month, assign_day, assign_year, due_month, due_day, due_year);
					PreparedStatement insertSpecificAssignment = null;

					try {
						insertSpecificAssignment = conn.prepareStatement(
							"insert into specific_assignments (assignment_id, video_id)" +
									" values (?, ?)"
						);
						insertSpecificAssignment.setInt(1, assignment_id);
						insertSpecificAssignment.setInt(2, getVideoIdByName(videoName));
						insertSpecificAssignment.executeUpdate();
					} finally {
						DBUtil.closeQuietly(insertSpecificAssignment);
					}
					
					return true;
				}
			});
		}
	}
	
	public void insertVideoByDisciplineAssignment(int class_id, String name, int assign_month, int assign_day, int assign_year, int due_month, int due_day, int due_year, String discipline, int video_count) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				Integer assignment_id = insertAssignment(class_id, name, assign_month, assign_day, assign_year, due_month, due_day, due_year);
				PreparedStatement insertVideoByDiscipline = null;

				try {
					insertVideoByDiscipline = conn.prepareStatement(
						"insert into discipline_assignments (assignment_id, discipline, video_count)" +
								" values (?, ?, ?)"
					);
					insertVideoByDiscipline.setInt(1, assignment_id);
					insertVideoByDiscipline.setString(2, discipline);
					insertVideoByDiscipline.setInt(3, video_count);
					insertVideoByDiscipline.executeUpdate();
				} finally {
					DBUtil.closeQuietly(insertVideoByDiscipline);
				}
				
				return true;
			}
		});
	}
	
	public ArrayList<Assignment> getAllAssignmentsForClass (int class_id) {
		return executeTransaction(new Transaction<ArrayList<Assignment>>() {
			@Override
			public ArrayList<Assignment> execute(Connection conn) throws SQLException {
				ArrayList<Assignment> assignments = new ArrayList<Assignment>();
				PreparedStatement getSpecificAssignments = null, getDisciplineAssignments = null;
				ResultSet specificResult = null, disciplineResult = null;
				
				try {
					getSpecificAssignments = conn.prepareStatement(
						"select assignments.class_id, assignments.name, assignments.assign_month, assignments.assign_day, assignments.assign_year," +
						" assignments.due_month, assignments.due_day, assignments.due_year, specific_assignments.video_id from specific_assignments, assignments where" +
						" specific_assignments.assignment_id = assignments.assignment_id" +
						" and assignments.class_id = ?"
					);
					getSpecificAssignments.setInt(1, class_id);
					
					specificResult = getSpecificAssignments.executeQuery();
					while (specificResult.next()) {
						assignments.add(new SpecificVideoAssignment(
							getYCPClassById(specificResult.getInt(1)),
							specificResult.getString(2),
							specificResult.getInt(3),
							specificResult.getInt(4),
							specificResult.getInt(5),
							specificResult.getInt(6),
							specificResult.getInt(7),
							specificResult.getInt(8),
							specificResult.getInt(9)
						));
					}
					
					getDisciplineAssignments = conn.prepareStatement(
						"select assignments.class_id, assignments.name, assignments.assign_month, assignments.assign_day, assignments.assign_year," +
						" assignments.due_month, assignments.due_day, assignments.due_year, discipline_assignments.discipline, discipline_assignments.video_count" +
						" from discipline_assignments, assignments where discipline_assignments.assignment_id = assignments.assignment_id" +
						" and assignments.class_id = ?"
					);
					getDisciplineAssignments.setInt(1, class_id);
						
					disciplineResult = getDisciplineAssignments.executeQuery();
					while (disciplineResult.next()) {
						assignments.add(new VideoByDisciplineAssignment(
							getYCPClassById(disciplineResult.getInt(1)),
							disciplineResult.getString(2),
							disciplineResult.getInt(3),
							disciplineResult.getInt(4),
							disciplineResult.getInt(5),
							disciplineResult.getInt(6),
							disciplineResult.getInt(7),
							disciplineResult.getInt(8),
							disciplineResult.getString(9),
							disciplineResult.getInt(10)
						));
					}
				} finally {
					DBUtil.closeQuietly(getSpecificAssignments);
					DBUtil.closeQuietly(getDisciplineAssignments);
					DBUtil.closeQuietly(specificResult);
					DBUtil.closeQuietly(disciplineResult);
				}
				
				return assignments;
			}
		});
	}
	
	public User getUserById(int user_id) throws SQLException {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement getUser = null;
				ResultSet result = null;

				try {
					getUser = conn.prepareStatement(
						"select * from users where user_id = ?"
					);
					getUser.setInt(1, user_id);
					
					result = getUser.executeQuery();
					
					if (result.next()) {
						return new User(
							result.getString(2),
							result.getString(3),
							result.getString(4),
							result.getString(5),
							result.getString(6),
							result.getInt(7),
							getDisciplinesForUserAsArr(user_id)
						);
					}
					
				} finally {
					DBUtil.closeQuietly(getUser);
					DBUtil.closeQuietly(result);
				}
				
				return null;
			}
		});
	}
	
	public Admin getAdminById(int admin_id) throws SQLException {
		return executeTransaction(new Transaction<Admin>() {
			@Override
			public Admin execute(Connection conn) throws SQLException {
				PreparedStatement getAdmin = null, getUser = null;
				ResultSet adminResult = null, userResult = null;

				try {
					getAdmin = conn.prepareStatement(
						"select * from admins where admin_id = ?"
					);
					getAdmin.setInt(1, admin_id);
					adminResult = getAdmin.executeQuery();
					
					if (adminResult.next()) {
						getUser = conn.prepareStatement(
							"select * from users where user_id = ?"
						);
						getUser.setInt(1, adminResult.getInt(2));
						userResult = getUser.executeQuery();
						
						if (userResult.next()) {
							return new Admin(
								userResult.getString(2),
								userResult.getString(3),
								userResult.getString(4),
								userResult.getString(5),
								userResult.getString(6),
								userResult.getInt(7),
								getDisciplinesForUserAsArr(userResult.getInt(1))
							);
						}
					}
					
				} finally {
					DBUtil.closeQuietly(getAdmin);
					DBUtil.closeQuietly(adminResult);
					DBUtil.closeQuietly(getUser);
					DBUtil.closeQuietly(userResult);
				}
				
				return null;
			}
		});
	}
	
	public User getUserByEmail(String email) throws SQLException {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement getUser = null;
				ResultSet result = null;

				try {
					getUser = conn.prepareStatement(
						"select * from users where email = ?"
					);
					getUser.setString(1, email);
					
					result = getUser.executeQuery();
					
					if (result.next()) {
						return new User(
							result.getString(2),
							result.getString(3),
							result.getString(4),
							result.getString(5),
							result.getString(6),
							result.getInt(7),
							getDisciplinesForUserAsArr(result.getInt(1))
						);
					}
					
				} finally {
					DBUtil.closeQuietly(getUser);
					DBUtil.closeQuietly(result);
				}
				
				return null;
			}
		});
	}
	
	public Boolean validCredentials(String email, String password) throws SQLException, IOException {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement checkUser = null;
				
				try {
					checkUser = conn.prepareStatement(
						"select * from users where email = ? and password = ?"
					);
					checkUser.setString(1, email);
					checkUser.setString(2, password);
					
					return checkUser.executeQuery().next();
				} finally {
					DBUtil.closeQuietly(checkUser);
				}
			}
		});
	}
	
	public Boolean userExists(String email) throws SQLException, IOException {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement checkUser = null;
				
				try {
					checkUser = conn.prepareStatement(
						"select * from users where email = ?"
					);
					checkUser.setString(1, email);
					
					return checkUser.executeQuery().next();
				} finally {
					DBUtil.closeQuietly(checkUser);
				}
			}
		});
	}
	
	public String getDisciplineById(int discipline_id) throws SQLException {
		return executeTransaction(new Transaction<String>() {
			@Override
			public String execute(Connection conn) throws SQLException {
				PreparedStatement getDiscipline = null;
				ResultSet result = null;

				try {
					getDiscipline = conn.prepareStatement(
						"select * from disciplines where discipline_id = ?"
					);
					getDiscipline.setInt(1, discipline_id);
					
					result = getDiscipline.executeQuery();
					
					if (result.next()) {
						return result.getString(2);
					}
					
				} finally {
					DBUtil.closeQuietly(getDiscipline);
					DBUtil.closeQuietly(result);
				}
				
				return null;
			}
		});
	}
	
	public Video getVideoById(int video_id) throws SQLException, IOException {
		return executeTransaction(new Transaction<Video>() {
			@Override
			public Video execute(Connection conn) throws SQLException {
				PreparedStatement getVideo = null;
				ResultSet result = null;

				try {
					getVideo = conn.prepareStatement(
						"select * from videos where video_id = ?"
					);
					getVideo.setInt(1, video_id);
					
					result = getVideo.executeQuery();
					
					if (result.next()) {
						try {
							return new Video(
								video_id,
								result.getString(2),
								result.getString(3),
								result.getString(4),
								result.getString(5),
								result.getString(6),
								result.getInt(7),
								result.getInt(8),
								result.getDouble(9),
								result.getInt(10),
								result.getInt(11),
								result.getInt(12),
								result.getString(13)
							);
						} catch (IOException e) {
							e.printStackTrace();
							return null;
						}
					}
					
				} finally {
					DBUtil.closeQuietly(getVideo);
					DBUtil.closeQuietly(result);
				}
				
				return null;
			}
		});
	}
	
	public Integer getVideoIdByName(String name) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement getVideo = null;
				ResultSet result = null;

				try {
					getVideo = conn.prepareStatement(
						"select video_id from videos where name = ?"
					);
					getVideo.setString(1, name);
					
					result = getVideo.executeQuery();
					
					if (result.next()) {
						return result.getInt(1);
					}
				} finally {
					DBUtil.closeQuietly(getVideo);
					DBUtil.closeQuietly(result);
				}
				
				return null;
			}
		});
	}
	
	public Boolean videoExistsByName(String name) {
		return getVideoIdByName(name) != null;
	}
	
	public Boolean videoExistsByUrl(String url) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement getVideo = null;
				
				try {
					final String key = "ted.com/talks/";
					if (url.indexOf(key) == -1) return false;
					
					getVideo = conn.prepareStatement(
						"select * from videos where url like %%?"
					);
					getVideo.setString(1, url.substring(url.indexOf(key) + key.length()));
					System.out.println(url.substring(url.indexOf(key) + key.length()));
					
					return getVideo.executeQuery().next();
				} finally {
					DBUtil.closeQuietly(getVideo);
				}
			}
		});
	}
	
	public Review getReviewById(int review_id) throws SQLException {
		return executeTransaction(new Transaction<Review>() {
			@Override
			public Review execute(Connection conn) throws SQLException {
				PreparedStatement getReview = null;
				ResultSet result = null;
				
				Review review = null;

				try {
					getReview = conn.prepareStatement(
						"select * from reviews where review_id = ?"
					);
					getReview.setInt(1, review_id);
					
					result = getReview.executeQuery();
					
					while (result.next()) {
						try {
							review = new Review(
								getUserById(result.getInt(2)),
								getVideoById(result.getInt(3)),
								result.getString(4),
								result.getInt(5),
								result.getInt(6),
								result.getInt(7),
								result.getInt(8),
								result.getInt(9),
								result.getInt(10) == 1,
								result.getInt(11) == 1,
								result.getInt(12) == 1,
								result.getInt(13) == 1,
								result.getInt(14) == 1,
								result.getInt(15) == 1,
								result.getInt(16) == 1
							);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				} finally {
					DBUtil.closeQuietly(getReview);
					DBUtil.closeQuietly(result);
				}
				
				return review;
			}
		});
	}
	
	public ArrayList<Review> getAllReviewsForVideo(Video video) throws SQLException, IOException {
		return executeTransaction(new Transaction<ArrayList<Review>>() {
			@Override
			public ArrayList<Review> execute(Connection conn) throws SQLException {
				ArrayList<Review> reviews = new ArrayList<Review> ();
				
				PreparedStatement getReview = null;
				ResultSet result = null;

				try {
					getReview = conn.prepareStatement(
						"select * from reviews where video_id = ?"
					);
					getReview.setInt(1, video.getId());
					
					result = getReview.executeQuery();
					
					while (result.next()) {
						reviews.add(new Review(
							getUserById(result.getInt(2)),
							video,
							result.getString(4),
							result.getInt(5),
							result.getInt(6),
							result.getInt(7),
							result.getInt(8),
							result.getInt(9),
							result.getInt(10) == 1,
							result.getInt(11) == 1,
							result.getInt(12) == 1,
							result.getInt(13) == 1,
							result.getInt(14) == 1,
							result.getInt(15) == 1,
							result.getInt(16) == 1
						));
					}
					
				} finally {
					DBUtil.closeQuietly(getReview);
					DBUtil.closeQuietly(result);
				}
				
				return reviews;
			}
		});
	}
	
	public ArrayList<String> getAllDisciplinesForUser(int user_id) throws SQLException {
		return executeTransaction(new Transaction<ArrayList<String>>() {
			@Override
			public ArrayList<String> execute(Connection conn) throws SQLException {
				ArrayList<String> disciplines = new ArrayList<String> ();
				
				PreparedStatement getDisciplines = null;
				ResultSet result = null;

				try {
					getDisciplines = conn.prepareStatement(
						"select disciplines.name from disciplines, user_disciplines " +
								"where disciplines.discipline_id = user_disciplines.discipline_id " +
								"and user_disciplines.user_id = ?"
					);
					getDisciplines.setInt(1, user_id);
					
					result = getDisciplines.executeQuery();
					
					while (result.next()) {
						disciplines.add(
							result.getString(1)
						);
					}
					
				} finally {
					DBUtil.closeQuietly(getDisciplines);
					DBUtil.closeQuietly(result);
				}
				
				return disciplines;
			}
		});
	}
	
	private String[] getDisciplinesForUserAsArr(int user_id) throws SQLException {
		ArrayList<String> list = getAllDisciplinesForUser(user_id);
		
		String[] arr = new String[list.size()];
		for (int i = 0; i < arr.length; i++) arr[i] = list.get(i);
		
		return arr;
	}
	
	public void editReview(int review_id, String newText) throws SQLException {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement editReview = null;
				
				try {
					editReview = conn.prepareStatement(
						"update reviews set text = ? where review_id = ?"
					);
					editReview.setString(1, newText);
					editReview.setInt(2, review_id);
					editReview.executeUpdate();
				} finally {
					DBUtil.closeQuietly(editReview);
				}
				
				return true;
			}
		});
	}
	
	public void deleteReview(int review_id) throws SQLException {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement deleteReview = null;
				
				Review review = getReviewById(review_id);
				if (review != null) {
					review.removeFromParent();
					try {
						deleteReview = conn.prepareStatement(
							"delete from reviews where review_id = ?"
						);
						deleteReview.setInt(1, review_id);
						deleteReview.executeUpdate();
					} finally {
						DBUtil.closeQuietly(deleteReview);
					}
				}
				
				return true;
			}
		});
	}
	
	public void deleteUserDiscipline(int discipline_id, int user_id) throws SQLException {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement deleteUserDiscipline = null;
				
				try {
					deleteUserDiscipline = conn.prepareStatement(
						"delete from user_disciplines where discipline_id = ? and user_id = ?"
					);
					deleteUserDiscipline.setInt(1, discipline_id);
					deleteUserDiscipline.setInt(2, user_id);
					deleteUserDiscipline.executeUpdate();
				} finally {
					DBUtil.closeQuietly(deleteUserDiscipline);
				}
				
				return true;
			}
		});
	}
	
	public ArrayList<User> getAllUsers() throws IOException {
		return executeTransaction(new Transaction<ArrayList<User>>() {
			public ArrayList<User> execute(Connection conn) throws SQLException {
				ArrayList<User> result = new ArrayList<User>();
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					stmt = conn.prepareStatement(
						"select * from users" 
					);
					resultSet = stmt.executeQuery();
					
						while (resultSet.next()) {
							result.add(new User(
								resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), 
								resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7),
								getDisciplinesForUserAsArr(resultSet.getInt(1))
							));
						}
					return result;
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
			}
		});			
	}
	
	public ArrayList<Video> getAllVideos() throws SQLException, IOException {
		return executeTransaction(new Transaction<ArrayList<Video>>() {
			@Override
			public ArrayList<Video> execute(Connection conn) throws SQLException {
				ArrayList<Video> videos = new ArrayList<Video> ();
				PreparedStatement getVideos = null;
				ResultSet result = null;

				try {
					getVideos = conn.prepareStatement(
						"select * from videos"
					);
					result = getVideos.executeQuery();
					
					while (result.next()) {
						try {
							videos.add(new Video(
								result.getInt(1),
								result.getString(2),
								result.getString(3),
								result.getString(4),
								result.getString(5),
								result.getString(6),
								result.getInt(7),
								result.getInt(8),
								result.getDouble(9),
								result.getInt(10),
								result.getInt(11),
								result.getInt(12),
								result.getString(13)
							));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} finally {
					DBUtil.closeQuietly(getVideos);
					DBUtil.closeQuietly(result);
				}
				
				return videos;
			}
		});
	}
	
	public ArrayList<Review> getAllReviews() throws SQLException {
		return executeTransaction(new Transaction<ArrayList<Review>>() {
			@Override
			public ArrayList<Review> execute(Connection conn) throws SQLException {
				ArrayList<Review> reviews = new ArrayList<Review>();
				PreparedStatement getReviews = null;
				ResultSet result = null;

				try {
					getReviews = conn.prepareStatement(
						"select * from reviews"
					);
					result = getReviews.executeQuery();
					
					while (result.next()) {
						try {
							reviews.add(new Review(
									getUserById(result.getInt(2)),
									getVideoById(result.getInt(3)),
									result.getString(4),
									result.getInt(5),
									result.getInt(6),
									result.getInt(7),
									result.getInt(8),
									result.getInt(9),
									result.getBoolean(10),
									result.getBoolean(11),
									result.getBoolean(12),
									result.getBoolean(13),
									result.getBoolean(14),
									result.getBoolean(15),
									result.getBoolean(16)
							));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} finally {
					DBUtil.closeQuietly(getReviews);
					DBUtil.closeQuietly(result);
				}
				
				return reviews;
			}
		});
	}
	
	public YCPClass getYCPClassById(int class_id) throws SQLException {
		return executeTransaction(new Transaction<YCPClass>() {
			@Override
			public YCPClass execute(Connection conn) throws SQLException {
				YCPClass ycpClass = null;
				
				PreparedStatement getYCPClass = null;
				ResultSet result = null;

				try {
					getYCPClass = conn.prepareStatement(
						"select * from classes where class_id = ?"
					);
					getYCPClass.setInt(1, class_id);
					
					result = getYCPClass.executeQuery();
					
					if (result.next()) {
						ycpClass = new YCPClass(
								getAdminById(result.getInt(2)),
								result.getString(3),
								result.getString(4),
								result.getInt(5)
						);
					}
					
				} finally {
					DBUtil.closeQuietly(getYCPClass);
					DBUtil.closeQuietly(result);
				}
				
				return ycpClass;
			}
		});
	}
	
	public ArrayList<YCPClass> getAllYCPClassesForUserById(int user_id) throws SQLException, IOException {
		return executeTransaction(new Transaction<ArrayList<YCPClass>>() {
			@Override
			public ArrayList<YCPClass> execute(Connection conn) throws SQLException {
				ArrayList<YCPClass> ycpClasses = new ArrayList<YCPClass> ();
				
				PreparedStatement getYCPClass = null;
				ResultSet result = null;

				try {
					getYCPClass = conn.prepareStatement(
						"select * from user_classes where user_id = ?"
					);
					getYCPClass.setInt(1, user_id);
					
					result = getYCPClass.executeQuery();
					
					while (result.next()) {
						ycpClasses.add(getYCPClassById(result.getInt(1)));
					}
					
				} finally {
					DBUtil.closeQuietly(getYCPClass);
					DBUtil.closeQuietly(result);
				}
				
				return ycpClasses;
			}
		});
	}
	
	public ArrayList<Video> getAllVideosFilteredBy(String key, String filter, String sort, int s, int f) throws SQLException, IOException {
		ArrayList<Video> videos = new ArrayList<Video> ();
		
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement getVideos = null;
				ResultSet result = null;

				try {
					getVideos = conn.prepareStatement(calculatedStatement(key, filter, sort));
					result = getVideos.executeQuery();
					
					int i = 0;
					
					while (result.next() && i < f) {
						i++;
						if (i >= s) {
							try {
								videos.add(new Video(
									result.getInt(1),
									result.getString(2),
									result.getString(3),
									result.getString(4),
									result.getString(5),
									result.getString(6),
									result.getInt(7),
									result.getInt(8),
									result.getDouble(9),
									result.getInt(10),
									result.getInt(11),
									result.getInt(12),
									result.getString(13)
								));
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				} finally {
					DBUtil.closeQuietly(getVideos);
					DBUtil.closeQuietly(result);
				}
				
				return true;
			}
		});
		
		return videos;
	}
	
	private String calculatedStatement(String key, String filter, String sort) {
		String stmt = "select * from videos where upper";
		
		if (filter == null) {
			stmt += ("(name) like upper('%" + key + "%') or upper(speaker) like upper('%" + key + "%') or upper(discipline_line) like upper('%" + key + "%') ");
		} else {
			switch (filter) {
				case "title":
					stmt += ("(name) like upper('%" + key + "%') ");
					break;
				case "speaker":
					stmt += ("(speaker) like upper('%" + key + "%') ");
					break;
				case "discipline":
					stmt += ("(discipline_line) like upper('%" + key + "%') ");
					break;
				default:
					stmt += ("(name) like upper('%" + key + "%') or upper(speaker) like upper('%" + key + "%') or upper(discipline_line) like upper('%" + key + "%') ");
					break;
			}
		}
		
		if (sort == null) {
			stmt += ("order by rating DESC");
		} else {
			switch (sort) {
				case "low":
					stmt += ("order by rating ASC");
					break;
				case "new":
					stmt += ("order by upload_year DESC, upload_month DESC, upload_day DESC");
					break;
				case "old":
					stmt += ("order by upload_year ASC, upload_month ASC, upload_day ASC");
					break;
				default:
					stmt += ("order by rating DESC");
					break;
			}
		}
		
		return stmt;
	}
	
	public ArrayList<Pair> getAllUserDisicplinePairs() {
		return executeTransaction(new Transaction<ArrayList<Pair>>() {
			@Override
			public ArrayList<Pair> execute(Connection conn) throws SQLException {
				ArrayList<Pair> pairs = new ArrayList<Pair> ();
				
				PreparedStatement getPairs = null;
				ResultSet result = null;

				try {
					getPairs = conn.prepareStatement(
						"select * from user_disciplines"
					);
					
					result = getPairs.executeQuery();
					
					while (result.next()) {
						pairs.add(new Pair("user_id", "discipline_id", result.getInt(1), result.getInt(2)));
					}
					
				} finally {
					DBUtil.closeQuietly(getPairs);
					DBUtil.closeQuietly(result);
				}
				
				return pairs;
			}
		});
		
	}
		public ArrayList<String> getAllDisciplines() throws SQLException {
			return executeTransaction(new Transaction<ArrayList<String>>() {
				@Override
				public ArrayList<String> execute(Connection conn) throws SQLException {
					ArrayList<String> disciplines = new ArrayList<String>();
					PreparedStatement getDisciplines = null;
					ResultSet result = null;

					try {
						getDisciplines = conn.prepareStatement(
							"select * from disciplines"
						);
						result = getDisciplines.executeQuery();
						
						while (result.next()) {
							disciplines.add(getDisciplineById(result.getInt(1)))
							;
						}
					} finally {
						DBUtil.closeQuietly(getDisciplines);
						DBUtil.closeQuietly(result);
					}
					
					return disciplines;
				}
			});
		}
		
		
	
		
	}
