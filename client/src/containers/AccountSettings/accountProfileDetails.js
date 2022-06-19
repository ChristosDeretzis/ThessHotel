import React, { useState } from 'react';
import {
  Box,
  Button,
  Card,
  CardContent,
  CardHeader,
  Checkbox,
  Divider,
  FormControl,
  FormControlLabel,
  FormGroup,
  FormLabel,
  Grid,
  Radio,
  RadioGroup,
  TextField
} from '@mui/material';
import { DesktopDatePicker } from '@mui/x-date-pickers/DesktopDatePicker';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { useDispatch, useSelector } from 'react-redux';
import { updateUser } from '../../store/user-slice';
import Snackbar from '@mui/material/Snackbar';
import MuiAlert from '@mui/material/Alert';
import AccountDelete from './accountDelete';

const Alert = React.forwardRef(function Alert(props, ref) {
  return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

export const AccountProfileDetails = (props) => {

  const dispatch = useDispatch();
  const user = JSON.parse(localStorage.getItem("userData"));
  const { message } = useSelector(state => state.message);

  const [isUpdateSuccessful, setUpdateSuccessfull] = useState(false);
  const [open, setOpen] = useState(false);
  const [hotelOwnerValue, setHotelOwnerValue] = useState(user.roles.includes("HOTEL_OWNER") ? "yes" : "no");

  const [values, setValues] = useState({
    id: user.id,
    firstName: user.firstName,
    lastName: user.lastName,
    email: user.email,
    username: user.username,
    dateOfBirth: new Date(user.dateOfBirth),
    state: user.state,
    country: user.country,
    city: user.city,
    strAddress: user.strAddress,
    strNumber: user.strNumber,
    zipCode: user.zipCode,
    roles: user.roles
  });

  const handleSubmit = () => {
    if(hotelOwnerValue === "yes"){
      setValues({
        ...values,
        roles: ["USER", "HOTEL_OWNER"]
      });
    } else {
      setValues({
        ...values,
        roles: ["USER"]
      });
    }
    
    dispatch(updateUser(values))
    .unwrap()
    .then((res) => {
      setUpdateSuccessfull(true);
      setOpen(true);
    }).catch((err) => {
      setUpdateSuccessfull(false);
      setOpen(true);
    });
  }

  const handleCloseSnackbar = () => {
    setOpen(false);
  }

  const handleChange = (event) => {
    setValues({
      ...values,
      [event.target.name]: event.target.value
    });
  };

  const handleHotelOwnerChange = (event) => {

    setHotelOwnerValue(event.target.value);
  }

  return (
    <form
      autoComplete="off"
      noValidate
      onSubmit={handleSubmit}
      {...props}
    >
      <Card>
        <CardHeader
          subheader="The information can be edited"
          title="Profile"
        />
        <Divider />
        <CardContent>
          <Grid
            container
            spacing={3}
          >
            <Grid
              item
              md={6}
              xs={12}
            >
              <TextField
                fullWidth
                helperText="Please specify the first name"
                label="First name"
                name="firstName"
                onChange={handleChange}
                required
                value={values.firstName}
                variant="outlined"
              />
            </Grid>
            <Grid
              item
              md={6}
              xs={12}
            >
              <TextField
                fullWidth
                label="Last name"
                name="lastName"
                onChange={handleChange}
                required
                value={values.lastName}
                variant="outlined"
              />
            </Grid>
            <Grid
              item
              md={6}
              xs={12}
            >
              <TextField
                fullWidth
                label="Email Address"
                name="email"
                onChange={handleChange}
                required
                value={values.email}
                variant="outlined"
              />
            </Grid>
            <Grid
              item
              md={6}
              xs={12}
            >
              <TextField
                fullWidth
                label="Username"
                name="username"
                onChange={handleChange}
                value={values.username}
                required
                variant="outlined"
              />
            </Grid>
            <Grid
              item
              md={12}
              xs={12}
            >
              <LocalizationProvider dateAdapter={AdapterDateFns}>
                <DesktopDatePicker
                  label="Date of Birth"
                  inputFormat="dd/MM/yyyy"
                  name="dateOfBirth"
                  value={new Date(values.dateOfBirth)}
                  onChange={(newValue) => {
                    setValues(prev => ({ ...prev, dateOfBirth: new Date(newValue)}))
                  }}
                  renderInput={(params) => <TextField fullWidth {...params} />}/>
              </LocalizationProvider>
            </Grid>
            <Grid
              item
              md={4}
              xs={12}
            >
              <TextField
                fullWidth
                label="Country"
                name="country"
                onChange={handleChange}
                required
                value={values.country}
                variant="outlined"
              />
            </Grid>
            <Grid
              item
              md={4}
              xs={12}
            >
              <TextField
                fullWidth
                label="State"
                name="state"
                onChange={handleChange}
                required
                value={values.state}
                variant="outlined"
              />
            </Grid>
            <Grid
              item
              md={4}
              xs={12}
            >
              <TextField
                fullWidth
                label="City"
                name="city"
                onChange={handleChange}
                required
                value={values.city}
                variant="outlined"
              />
            </Grid>
            <Grid
              item
              md={6}
              xs={12}
            >
              <TextField
                fullWidth
                label="Street Address"
                name="strAddress"
                onChange={handleChange}
                required
                value={values.strAddress}
                variant="outlined"
              />
            </Grid>
            <Grid
              item
              md={3}
              xs={12}
            >
              <TextField
                fullWidth
                label="Street Number"
                name="strNumber"
                onChange={handleChange}
                required
                value={values.strNumber}
                variant="outlined"
                type="number"
              />
            </Grid>
            <Grid
              item
              md={3}
              xs={12}
            >
              <TextField
                fullWidth
                label="Zip Code"
                name="zipCode"
                onChange={handleChange}
                required
                value={values.zipCode}
                variant="outlined"
                type="number"
              />
            </Grid>
            <Grid
              item
              md={6}
              xs={12}
            >
              <FormControl>
                <FormLabel id="demo-controlled-radio-buttons-group" >Are you a hotel owner ?</FormLabel>
                <RadioGroup
                  row
                  aria-labelledby="demo-controlled-radio-buttons-group"
                  name="controlled-radio-buttons-group"
                  value={hotelOwnerValue}
                  onClick={handleHotelOwnerChange}
                >
                  <FormControlLabel value="yes" control={<Radio />} label="Yes" />
                  <FormControlLabel value="no" control={<Radio />} label="No" />
                </RadioGroup>
              </FormControl>
            </Grid>
          </Grid>
        </CardContent>
        <Divider />
        <Box
          sx={{
            display: 'flex',
            justifyContent: 'space-between',
            p: 2
          }}
        >
          <AccountDelete />
          <Button
            color="primary"
            variant="contained"
            onClick={handleSubmit}
          >
            Save details
          </Button>
          <Snackbar open={open} autoHideDuration={6000} onClose={handleCloseSnackbar}>
            {isUpdateSuccessful ? 
                (
                <Alert onClose={handleCloseSnackbar} severity="success" sx={{ width: '100%' }}>
                  {message}
                </Alert>
                )
            : (
              <Alert onClose={handleCloseSnackbar} severity="error" sx={{ width: '100%' }}>
                  {message}
                </Alert>
            )}
          </Snackbar>
        </Box>
      </Card>
    </form>
  );
};