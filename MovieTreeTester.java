//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Movie Catalog
// Course: CS 300 Winter 2021
//
// Author: Qi Feng
// Email: qfeng43@wisc.edu
// Lecturer: (Mouna Kacem or Hobbes LeGault)
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

// Add javadoc style class header here
// File Header comes here
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * MovieTree.
 *
 */

public class MovieTreeTester {

  /**
   * Checks the correctness of the implementation of both addMovie() and toString() methods
   * implemented in the MovieTree class. This unit test considers at least the following scenarios.
   * (1) Create a new empty MovieTree, and check that its size is 0, it is empty, and that its
   * string representation is an empty string "". (2) try adding one movie and then check that the
   * addMovie() method call returns true, the tree is not empty, its size is 1, and the .toString()
   * called on the tree returns the expected output. (3) Try adding another movie which is smaller
   * that the movie at the root, (4) Try adding a third movie which is greater than the one at the
   * root, (5) Try adding at least two further movies such that one must be added at the left
   * subtree, and the other at the right subtree. For all the above scenarios, and more, double
   * check each time that size() method returns the expected value, the add method call returns
   * true, and that the .toString() method returns the expected string representation of the
   * contents of the binary search tree in an increasing order from the smallest to the greatest
   * movie with respect to year, rating, and then name. (6) Try adding a movie already stored in the
   * tree. Make sure that the addMovie() method call returned false, and that the size of the tree
   * did not change.
   * 
   * @param BSTNode<Movie> MovieTree
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddMovieToStringSize() {
    // (1) Create a new empty MovieTree, and check that its size is 0, it is empty, and that its
    // string representation is an empty string "".
    MovieTree MovieTree = new MovieTree();
    if (MovieTree.size() != 0 || !MovieTree.isEmpty() || !MovieTree.toString().equals("")) {
      return false;
    }
    // (2)try adding one movie
    Movie movie = new Movie(2015, 10.0, "apple");
    MovieTree.addMovie(movie);
    if (!MovieTree.toString().equals("[(Year: 2015) (Rate: 10.0) (Name: apple)]\n")) {
      System.out.println("Case2 has some problem.");
      System.out.println(MovieTree.toString());
      return false;
    }

    // (3) Try adding another movie which is smaller that the movie at the root
    Movie movie1 = new Movie(1995, 10.0, "apple2");
    MovieTree.addMovie(movie1);
    if (!MovieTree.toString().equals("[(Year: 1995) (Rate: 10.0) (Name: apple2)]\n"
        + "[(Year: 2015) (Rate: 10.0) (Name: apple)]\n")) {
      System.out.println("Case3 has some problem.");
      System.out.println(MovieTree.toString());
      return false;
    }

    // (4) Try adding a third movie which is greater than the one at the root
    Movie movie2 = new Movie(2016, 10.0, "apple3");
    MovieTree.addMovie(movie2);
    if (!MovieTree.toString()
        .equals("[(Year: 1995) (Rate: 10.0) (Name: apple2)]\n"
            + "[(Year: 2015) (Rate: 10.0) (Name: apple)]\n"
            + "[(Year: 2016) (Rate: 10.0) (Name: apple3)]\n")) {

      System.out.println("Case4 has some problem.");
      System.out.println(MovieTree.toString());
      return false;
    }

    // (5)add 2 more,check the size tostring, return of the add method
    Movie movie3 = new Movie(2017, 10.0, "apple3");
    Movie movie4 = new Movie(1994, 10.0, "apple4");
    MovieTree.addMovie(movie3);
    MovieTree.addMovie(movie4);
    if (MovieTree.size() != 5 || !MovieTree.toString()
        .equals("[(Year: 1994) (Rate: 10.0) (Name: apple4)]\n"
            + "[(Year: 1995) (Rate: 10.0) (Name: apple2)]\n"
            + "[(Year: 2015) (Rate: 10.0) (Name: apple)]\n"
            + "[(Year: 2016) (Rate: 10.0) (Name: apple3)]\n"
            + "[(Year: 2017) (Rate: 10.0) (Name: apple3)]\n")) {
      System.out.println("Case5 has some problem.");
      System.out.println(MovieTree.toString());
      System.out.println(MovieTree.size());
      return false;
    }

    // (6)adding the same
    if (MovieTree.addMovie(movie3) == true) {
      return false;
    }
    return true;
  }

  /**
   * This method checks mainly for the correctness of the MovieTree.contains() method. It must
   * consider at least the following test scenarios. (1) Create a new MovieTree. Then, check that
   * calling the contains() method on an empty MovieTree returns false. (2) Consider a MovieTree of
   * height 3 which contains at least 5 movies. Then, try to call contains() method to search for
   * the movie having a match at the root of the tree. (3) Then, search for a movie at the right and
   * left subtrees at different levels considering successful and unsuccessful search operations.
   * Make sure that the contains() method returns the expected output for every method call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testContains() {
    // (1) Create a new MovieTree
    MovieTree MovieTree = new MovieTree();
    if (MovieTree.contains(2000, 10.0, "apple")) {
      System.out.println("Case1 has some problem.");
      return false;
    }

    // (2) add 5 movies on the list, match the selected
    Movie movie = new Movie(2000, 10.0, "apple");
    Movie movie2 = new Movie(1998, 10.0, "apple1");
    Movie movie3 = new Movie(1999, 10.0, "apple2");
    Movie movie4 = new Movie(2003, 10.0, "apple3");
    Movie movie5 = new Movie(2002, 10.0, "apple4");
    MovieTree.addMovie(movie);
    MovieTree.addMovie(movie2);
    MovieTree.addMovie(movie3);
    MovieTree.addMovie(movie4);
    MovieTree.addMovie(movie5);

    if (!MovieTree.contains(2000, 10.0, "apple")) {
      System.out.println("Case2 has some problem.");
      return false;
    }

    // search for a movie at the right and left
    // search left
    if (!MovieTree.contains(2003, 10.0, "apple3") || !MovieTree.contains(1998, 10.0, "apple1")) {
      System.out.println("Case2 has some problem.");
      return false;
    }



    return true;
  }

  /**
   * Checks for the correctness of MovieTree.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty movie tree is zero. (2) ensures that
   * the height of a tree which consists of only one node is 1. (3) ensures that the height of a
   * MovieTree with the following structure for instance, is 4. (*) / \ (*) (*) \ / \ (*) (*) (*) /
   * (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    MovieTree MovieTree = new MovieTree();
    // (1)ensures that the height of an empty movie tree is zero.
    if (MovieTree.height() != 0) {
      System.out.println("Case1 has some problem.");
      return false;
    }
    Movie movie = new Movie(2000, 10.0, "apple");
    Movie movie2 = new Movie(1998, 10.0, "apple1");
    Movie movie3 = new Movie(1999, 10.0, "apple2");
    Movie movie4 = new Movie(2003, 10.0, "apple3");
    Movie movie5 = new Movie(2002, 10.0, "apple4");
    Movie movie6 = new Movie(2006, 10.0, "apple5");
    Movie movie7 = new Movie(2005, 10.0, "apple6");

    // (2)ensures that the height of a tree which consists of only one node is 1
    MovieTree.addMovie(movie);
    if (MovieTree.height() != 1) {
      System.out.println("Case2 has some problem.");
      return false;
    }
    // (3) ensures that the height of a MovieTree with the following structure for instance, is 4
    MovieTree.addMovie(movie2);
    MovieTree.addMovie(movie3);
    MovieTree.addMovie(movie4);
    MovieTree.addMovie(movie5);
    MovieTree.addMovie(movie6);
    MovieTree.addMovie(movie7);
    if (MovieTree.height() != 4) {
      System.out.println("Case3 has some problem.");
      return false;
    }



    return true;
  }

  /**
   * Checks for the correctness of MovieTree.getBestMovie() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestMovie() {
    try {
      MovieTree MovieTree = new MovieTree();
      Movie movie = new Movie(2000, 10.0, "apple");
      Movie movie2 = new Movie(1998, 10.0, "apple1");
      Movie movie3 = new Movie(1999, 10.0, "apple2");
      Movie movie4 = new Movie(2003, 10.0, "apple3");
      Movie movie5 = new Movie(2002, 10.0, "apple4");
      Movie movie6 = new Movie(2006, 10.0, "apple5");
      Movie movie7 = new Movie(2005, 10.0, "apple6");
      MovieTree.addMovie(movie);
      MovieTree.addMovie(movie2);
      MovieTree.addMovie(movie3);
      MovieTree.addMovie(movie4);
      MovieTree.addMovie(movie5);
      MovieTree.addMovie(movie6);
      MovieTree.addMovie(movie7);
      if (!MovieTree.getBestMovie().equals(movie6)) {
        System.out.println(MovieTree.getBestMovie());
        System.out.println("Case1 has some problem.");
        return false;
      }
    }
    catch (Exception e) { // an unexpected exception was thrown
      e.printStackTrace();
      return false;
      }
    
    return true;  
  }

  /**
   * Checks for the correctness of MovieTree.lookup() method. This test must consider at least 3
   * test scenarios. (1) Ensures that the MovieTree.lookup() method throws a NoSuchElementException
   * when called on an empty tree. (2) Ensures that the MovieTree.lookup() method returns an array
   * list which contains all the movies satisfying the search criteria of year and rating, when
   * called on a non empty movie tree with one match, and two matches and more. Vary your search
   * criteria such that the lookup() method must check in left and right subtrees. (3) Ensures that
   * the MovieTree.lookup() method throws a NoSuchElementException when called on a non-empty movie
   * tree with no search results found.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    ArrayList<Movie> list;
    // (1)Ensures that the MovieTree.lookup() method throws a NoSuchElementException
    MovieTree MovieTree = new MovieTree();
    try {
      MovieTree.lookup(2000, 10.0);
    } catch (NoSuchElementException e) {
      System.out.println(e);
    }
    // (2)Ensures that the MovieTree.lookup() method returns an array
    Movie movie = new Movie(2000, 10.0, "apple");
    Movie movie1 = new Movie(2000, 9.0, "apple");
    MovieTree.addMovie(movie);
    MovieTree.addMovie(movie1);
    list = MovieTree.lookup(2000, 10.0);
    if (!list.get(0).equals(movie)) {
      System.out.println("case 2 is wrong.");
      return false;
    }
    // Ensures that the MovieTree.lookup() method throws a NoSuchElementException when called on a
    // non-empty movie tree with no search results found.
    try {
      MovieTree.lookup(2050, 10.0);
    }catch (NoSuchElementException e) {
      System.out.println(e);
    }
    return true;
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println(testAddMovieToStringSize());
    System.out.println(testContains());
    System.out.println(testHeight());
    System.out.println(testGetBestMovie());
    System.out.println(testLookup());

  }

}
