import React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Slide from '@mui/material/Slide';
import { useDispatch } from 'react-redux';
import { deleteUser } from '../../store/user-slice';
import { useNavigate } from 'react-router';

const AccountDelete = (props) => {
  const [open, setOpen] = React.useState(false);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const userId = JSON.parse(localStorage.getItem("userData")).id;

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClickCancel = () => {
    setOpen(false);
  };

  const handleClickOk = () => {
      dispatch(deleteUser(userId))
      .unwrap()
      .then((res) => {
          console.log(res);
          window.location.href = "/login";
      }).catch((err) => {
          console.log(err);
      })
      setOpen(false);
  }

  return (
    <div>
      <Button variant="contained" color="error" onClick={handleClickOpen}>
        Delete Account
      </Button>
      <Dialog
        open={open}
        keepMounted
        onClose={handleClickCancel}
        aria-describedby="alert-dialog-slide-description"
      >
        <DialogTitle>{"Are you sure you want to delete account?"}</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-slide-description">
            All of your account data will be permanently deleted
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClickCancel}>Cancel</Button>
          <Button onClick={handleClickOk}>Delete Account</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}

export default AccountDelete;