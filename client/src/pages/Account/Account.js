import { Box, Container, Grid, Typography } from '@mui/material';
import { AccountProfileDetails } from '../../containers/AccountSettings/accountProfileDetails';

const Account = () => (
  <>
    <Box
      component="main"
      sx={{
        flexGrow: 1,
        py: 4,
        alignItems: 'center'
      }}
    >
      <Container maxWidth="lg">
        <Grid
          container
          spacing={0}
          justifyContent="center"
          maxWidth="lg"
        >
          <Grid
            item
            lg={8}
            md={10}
            xs={12}
          >
            <AccountProfileDetails />
          </Grid>
        </Grid>
      </Container>
    </Box>
  </>
);

export default Account;