import * as React from 'react';
import Box from '@mui/material/Box';
import Avatar from '@mui/material/Avatar';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import ListItemIcon from '@mui/material/ListItemIcon';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Tooltip from '@mui/material/Tooltip';
import PersonAdd from '@mui/icons-material/PersonAdd';
import Settings from '@mui/icons-material/Settings';
import Logout from '@mui/icons-material/Logout';
import { Link, useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { deepOrange, deepPurple } from '@mui/material/colors';

const AccountMenu=()=> {

  const [firstInitial, setFirstInitial] = useState("");
  const [userDetails, setUserDetails] = useState(null);
  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);
  const navigate = useNavigate();


  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };


  const handleAvatarClick = async() => {

    try {
      const response = await fetch('http://localhost:8080/logout', {
          method: 'POST',
          headers: {
              'Authorization': `Bearer ${localStorage.getItem('JWTtoken')}`,
          },
      });
  
      if (response.ok) {
  
          localStorage.removeItem('JWTtoken');
          console.log("clicked")
          localStorage.removeItem("userDetails");
          setTimeout(() => {
            window.location.reload();
          }, 200);
          if (userDetails==null) {
            navigate("/signin");
          }
  
      } else {
          console.error('Logout failed');
        
      }
  } catch (error) {
      console.error('Error:', error);
      
  }
  
   
  };

  useEffect(() => {
    let userDetails = JSON.parse(localStorage.getItem("userDetails"));
    if (userDetails != null) {
      let initial = userDetails.firstName[0].toUpperCase();
      setFirstInitial(initial);
    } else {
      setUserDetails(null);
    }
  }, []);


  return (
    <React.Fragment>
      <Box sx={{ display: 'flex', alignItems: 'center', textAlign: 'center' }}>
        <Tooltip title="Account settings">
          <IconButton
            onClick={handleClick}
            size="small"
            sx={{ ml: 2 }}
            aria-controls={open ? 'account-menu' : undefined}
            aria-haspopup="true"
            aria-expanded={open ? 'true' : undefined}
          >
            <Avatar sx={{
                    bgcolor: deepOrange[500],
                    width: '30px',
                    height: '30px',
                  }}>{firstInitial}</Avatar>
          </IconButton>
        </Tooltip>
      </Box>
      <Menu
        anchorEl={anchorEl}
        id="account-menu"
        open={open}
        onClose={handleClose}
        onClick={handleClose}
        PaperProps={{
          elevation: 0,
          sx: {
            overflow: 'visible',
            filter: 'drop-shadow(0px 2px 8px rgba(0,0,0,0.32))',
            mt: 1.5,
            '& .MuiAvatar-root': {
              width: 32,
              height: 32,
              ml: -0.5,
              mr: 1,
            },
            '&:before': {
              content: '""',
              display: 'block',
              position: 'absolute',
              top: 0,
              right: 14,
              width: 10,
              height: 10,
              bgcolor: 'background.paper',
              transform: 'translateY(-50%) rotate(45deg)',
              zIndex: 0,
            },
          },
        }}
        transformOrigin={{ horizontal: 'right', vertical: 'top' }}
        anchorOrigin={{ horizontal: 'right', vertical: 'bottom' }}
      >
       <Link to="/profile" style={{ textDecoration: 'none' , color :"black"}}>
        <MenuItem onClick={handleClose}>
          <Avatar /> Profile
        </MenuItem>
       </Link> 

      <Link to="/forum" style={{ textDecoration: 'none' , color:"black"}}>
        <MenuItem onClick={handleClose}>
          <Avatar />My account 
        </MenuItem>
      </Link>

        <Divider />
        <MenuItem onClick={handleClose}>
          <ListItemIcon>
            <PersonAdd fontSize="small" />
          </ListItemIcon>
          Add another account
        </MenuItem>
        <MenuItem onClick={handleClose}>
          <ListItemIcon>
            <Settings fontSize="small" />
          </ListItemIcon>
          Settings
        </MenuItem>
        <MenuItem onClick={handleAvatarClick}>
          <ListItemIcon>
            <Logout fontSize="small" />
          </ListItemIcon>
          Logout
        </MenuItem>
      </Menu>
    </React.Fragment>
  );
}

export{AccountMenu};