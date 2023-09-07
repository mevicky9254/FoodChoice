import React, { useState,useEffect} from 'react';
import './chatbot.css';

const Chatbot = () => {
    
  const [userInput, setUserInput] = useState('');
  const [userData, setUserData] = useState({});
  const [username, setUsername] = useState('');
  const [messages, setMessages] = useState([
    { text: `Hello ${username}, how can I assist you?`, type: 'assistant' } // Default greeting message
  ]);
  const handleSendMessage = async () => {
    if (userInput.trim() === '') return;
  
    // Add user input to the messages state
    setMessages(prevMessages => [...prevMessages, { text: userInput, type: 'user' }]);
  
    // Call the OpenAI API to get a response
    try {
      const response = await fetchOpenAIResponse(userInput);
      setMessages(prevMessages => [...prevMessages, { text: response, type: 'assistant' }]);
    } catch (error) {
      console.error('Error:', error);
      setMessages(prevMessages => [...prevMessages, { text: 'An error occurred while fetching the response.', type: 'assistant' }]);
    }
  
    // Clear the user input field
    setUserInput('');
  };


  useEffect(() => {
    const storedUserDetails = localStorage.getItem('userDetails');
    const storedToken = localStorage.getItem('JWTtoken');

    if (storedUserDetails && storedToken) {
      const parsedUserDetails = JSON.parse(storedUserDetails);
      setUserData(parsedUserDetails);
      setUsername(parsedUserDetails.firstName);
      
    }
  }, []);


  const fetchOpenAIResponse = async (userMessage) => {

    console.log("fetch chat")

    try {
      const response = await fetch(`http://localhost:8080/chat?prompt=${(userMessage)}`, {
        method: 'GET', // Specify the HTTP method (GET in this case)
      });
  
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
  
      const result = await response.text();
      console.log(result);
      return result;
    } catch (error) {
      throw error;
    }
    
  };

  return (
    <div className="chat-container">
      <div className="chat-header">FoodChoice Assistant</div>
      <div className="chat-messages" id="chat-messages">
        {messages.map((message, index) => (
            <div key={index} className={`message ${message.type === 'user' ? 'user' : 'assistant'}`}>
               {message.text}
            </div>
        ))}
      </div>
      <div className="message-container">
        <input
          type="text"
          id="user-input-textarea"
          className="message-input"
          placeholder="Type your message..."
          value={userInput}
          onChange={(e) => setUserInput(e.target.value)}
        />
        <button className="send-button" onClick={handleSendMessage}>
          Send
        </button>
      </div>
    </div>
  );
};

export { Chatbot };
