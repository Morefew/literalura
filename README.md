##  **Literalura: A Text-Based Interactive Book Catalog**

### **Project Description**

**Literalura** is a Java application that allows users to interact with a book catalog through a text-based console interface. Users can search for books by title, view saved books, explore authors, filter authors by time period, and list books by language.

**Technologies Used:**

*   **Java:** The primary programming language for the application.
*   **Spring Boot:** A framework for building Java applications, providing features like dependency injection, auto-configuration, and embedded servers.
*   **Spring Data JPA:** Simplifies database access and object-relational mapping.
*   **PostgreSQL:** The chosen relational database for storing book and author information.
*   **Jackson Libraries:** For handling JSON data parsing and serialization.
*   **Gutendex API:** An external API used to fetch book information.

**Challenges Faced and Future Features:**

The available source code does not provide information about specific challenges encountered during development or plans for future features.

### **Table of Contents**

*   Installation
*   How to Use
*   Credits
*   License
*   Disclaimer of Warranty

### **Installation**

**Prerequisites:**

*   **Java Development Kit (JDK):** Version 17 is specified in the POM.xml.
*   **PostgreSQL:** A running PostgreSQL database instance is required.
*   **Maven:** The project uses Maven as its build tool.

**Steps:**

1.  **Clone the Repository:**
    ```bash
    git clone https://github.com/Morefew/literalura.git
    ```
2.  **Navigate to the Project Directory:**
    ```bash
    cd literalura
    ```
3.  **Configure Database Connection:**
    *   Update the `src/main/resources/application.properties` file with your PostgreSQL database connection details:
        ```
        spring.datasource.url=jdbc:postgresql://{DB_HOST}:{DB_PORT}/literalura
        spring.datasource.username={DB_USER}
        spring.datasource.password={DB_PASS}
        ```
4.  **Build the Project:**
    ```bash
    mvn clean install
    ```
5.  **Run the Application:**
    *   Execute the generated JAR file:
        ```bash
        java -jar target/literalura-0.0.1-SNAPSHOT.jar
        ```
    *   The application will start and present the interactive menu.

### **How to Use**

**Interacting with the Application:**

Literalura provides a text-based menu-driven interface:

*   **Search for a Book by Title:** Users can input a book title, and the application retrieves matching books from the Gutendex API using the base URL `https://gutendex.com/books/?search=`. The results display the book's ID, title, author, language, and download count. The application returns an error message if there are no results.
*   **List Saved Books:** Displays a list of books previously saved by the user from search results. Books are sorted alphabetically by title.
*   **List All Authors:** Shows a list of all authors retrieved from the Gutendex API. This feature assumes author information is stored along with saved books. Authors are sorted alphabetically by name.
*   **List Authors Alive During a Time Period:** Allows users to specify a start and end year to filter authors who were alive within that range.
*   **List Books by Language:** Enables users to search for books in a specific language.

**Example Usage:**

*   To search for "Pride and Prejudice":
    ```
    1 - Buscar Libro por Título
    ```
    *   Enter the title: "Pride and Prejudice".
    *   The application will display matching books from the Gutendex API.
*   To list books saved in the database:
    ```
    2 - Lista de Libros Salvados 
    ```
    *   The application will show the saved books.

### **Credits**

*   **Developer:** Morefew
*   **API Used:** Gutendex API

### **License**

This project is licensed under the **MIT License**.

### **Disclaimer of Warranty**

**THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.** 

