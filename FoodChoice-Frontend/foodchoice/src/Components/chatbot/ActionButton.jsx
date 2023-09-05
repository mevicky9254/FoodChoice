import * as React from 'react';
import Box from '@mui/material/Box';
import SpeedDial from '@mui/material/SpeedDial';
import ChatIcon from '@mui/icons-material/Chat';
import { Chatbot } from './Chatbot';


const ActionButton = () => {
  const [isChatbotVisible, setIsChatbotVisible] = React.useState(false);

  const toggleChatbot = () => {
    setIsChatbotVisible(!isChatbotVisible);
  };

  return (
    <Box sx={{ position: 'fixed', bottom: '70px', right: '35px', zIndex: '100' }}>
      <SpeedDial
        ariaLabel="SpeedDial openIcon example"
        sx={{ position: 'absolute', bottom: '-40px', right: '0px' }}
        icon={
          <ChatIcon
            openIcon={<ChatIcon sx={{ fontSize: '32px', color: 'white' }} />}
            sx={{
              fontSize: '32px',
              color: 'white',
              borderRadius: '50%',
              backgroundColor: '#0074cc', 
              '&:hover': {
                backgroundColor: '#0056a0', 
              },
            }}
          />
        }
        onClick={toggleChatbot}
        open={isChatbotVisible}
        direction="up"
      >
      </SpeedDial>
      {isChatbotVisible && (
        <div className="chatbot-container">
          <Chatbot />
        </div>
      )}
    </Box>
  );
}

export { ActionButton };
