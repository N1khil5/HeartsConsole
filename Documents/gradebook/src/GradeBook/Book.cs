using System.Collections.Generic;


namespace GradeBook
{
    public class Book
    {
        public Book(string name)
        {
            grades = new List<double>();
            this.name = name;
        }

        public void AddGrade(double grade)
        {
            this.grades.Add(grade);
        }

        public Statistics getStatistics() 
        {
            var result = new Statistics();
            result.Low = double.MaxValue;
            result.High = double.MinValue;
            result.Average = 0.0;
            foreach(var grade in grades)
            {
                result.Low = Math.Min(grade, result.Low);
                result.High = Math.Max(grade, result.High);
                result.Average += grade;
            }
            result.Average /= grades.Count;
            return result;
        }

        private List<double> grades;
        private string name;
    }
}