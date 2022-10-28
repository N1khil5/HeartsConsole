namespace GradeBook.Tests
{
    public class BookTests
    {
        [Fact] //attribute
        public void Test1()
        {
            // Arrange
            var book = new Book("");
            book.AddGrade(89.1);
            book.AddGrade(90.5);
            book.AddGrade(77.5);

            // Act
            var result = book.getStatistics();

            // Assert
            Assert.Equal(85.7, result.Average, 1);
            Assert.Equal(90.5, result.High,1);
            Assert.Equal(77.5, result.Low,1);
        }
    }
}